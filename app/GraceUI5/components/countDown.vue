<template>
	<view class="">
		<view class="box">
			<view class="circle" :style="[circle]">
				<view class="left" :style="[size]">
					<view class="left-circle"
						:style="[{'animation':stycircle1},{	'animation-delay': animationDelay },{'animation-play-state':pause_text},size,leftCircle]">
					</view>
				</view>
				<view class="right" :style="[size]">
					<view class="right-circle"
						:style="[{'animation':stycircle2},{'animation-play-state':pause_text},size,rightcircle]"></view>
				</view>
				<view class="inner" :style="[inner]">
					<slot></slot>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	/*
	 * countDown 圆环计时器，不使用canvas,仅css实现,圆环中间支持自定义添加内容
	 *  @property {Number} times 时间 (单位s)
	 * @property {Number} widths 圆环的总体大小 (单位upx)
	 * @property {Number} breadth 圆环中间区域的大小 (单位upx)
	 * @property {activeColor} activeColor 圆环中间区域的背景色
	 * @property {String} defaultColor 圆环中间区域默认的背景颜色
	 */
	export default {
		name : "countDown",
		props: {
			times: {
				type: Number,
				default: 10
			},
			widths: {
				type: Number,
				default: 200
			},
			breadth: {
				type: Number,
				default: 30
			},
			activeColor: {
				type: String,
				default: '#01B862'
			},
			defaultColor: {
				type: String,
				default: '#EDF0F0'
			},
		},
		data() {
			return {
				stycircle1: {},
				stycircle2: {},
				animationDelay: 0,
				timer: null,
				timeAll: 0, //时间
				pause_text: '', //暂停

				finish: false, //结束标识 true结束 false未开始
			}
		},
		destroyed() { //销毁后
			clearInterval(this.timer)
		},
		mounted() {},

		methods: {
			start() { //开始事件
				if (this.finish) {
					return
				}
				this.$nextTick(() => {
					if (this.times < 1) {
						uni.showToast({
							title: '时间最少1s',
							icon: 'none',
							duration: 1500,
							mask: true
						});
					} else {
						clearInterval(this.timer)
						let time = Number((this.times / 2).toFixed(2))
						this.stycircle2 = `progross2 ${time}s linear forwards`
						this.animationDelay = `${time}s`
						this.stycircle1 = `progross1 ${time}s linear forwards`
						this.timeAll = JSON.parse(JSON.stringify(this.times))
						this.setIn()
					}
				})
			},
			setIn() { //计时器
				this.timer = setInterval(() => {
					this.timeAll--
					console.log(this.timeAll);
					if (this.timeAll < 1) {
						this.finish = true
						this.$emit('endTime')
						clearInterval(this.timer)
					}
				}, 1000)
			},
			pause() { //暂停
				if (this.finish) {
					return
				} 
				this.pause_text = 'paused'
				clearInterval(this.timer)
				this.$emit('pause', this.timeAll)
			},
			goOn() { //继续
				if (this.pause_text == '' || this.pause_text == 'running') {
					return
				}
				this.pause_text = 'running'
				this.setIn()
			},
			reset() { //重置
				console.log('重置');
				this.stycircle2 = `progross 0s linear forwards`
				this.stycircle1 = `progross 0s linear forwards`
				this.animationDelay = 0
				clearInterval(this.timer)
				this.finish = false
			},
		},
		computed: {
			circle() {
				const {
					widths,
					defaultColor
				} = this
				return {
					width: `${widths}rpx`,
					height: `${widths}rpx`,
					background: defaultColor
				}
			},
			size() {
				const {
					widths
				} = this
				return {
					width: `${widths /2 +1}rpx`,
					height: `${widths + 1}rpx`
				}
			},
			leftCircle() {
				const {
					widths,
					activeColor
				} = this
				return {
					borderTopLeftRadius: `${widths}rpx`,
					borderBottomLeftRadius: `${widths}rpx`,
					background: activeColor
				}
			},
			rightcircle() {
				const {
					widths,
					activeColor
				} = this
				return {
					borderTopRightRadius: `${widths}rpx`,
					borderBottomRightRadius: `${widths}rpx`,
					background: activeColor
				}
			},
			inner() {
				const {
					widths,
					breadth
				} = this
				return {
					width: `${widths - breadth}rpx`,
					height: `${widths - breadth}rpx`,
				}
			}
		},
		watch: {

		}
	}
</script>

<style>
	.circle {
		border-radius: 50%;
		position: relative;
	}

	.left,
	.right {
		position: absolute;
		overflow: hidden;
	}

	.left-circle {
		transition: all .5s;
		transform-origin: right center;
		transform: rotate(180deg);
	}

	.right-circle {
		transition: all .5s;
		transform-origin: left center;
		transform: rotate(-180deg);
	}

	@keyframes progross1 {
		to {
			transform: rotate(360deg);
		}
	}

	@keyframes progross2 {
		to {
			transform: rotate(0deg);
		}
	}

	.right {
		right: 0;
	}

	.inner {
		background: #fff;
		position: absolute;
		z-index: 999;
		border-radius: 100%;
		left: 50%;
		top: 50%;
		transform: translate(-50%, -50%);
		overflow: hidden;
		display: flex;
		align-items: center;
		justify-content: center;
	}
</style>
