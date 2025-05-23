<!--
 * @Description: 自定义 悬浮异形 tabbar 
-->
<template>
	<view class="tqb-tabbar">
		<view class="tqb-tabbar-popup" v-if="visible" @click="visible = false">
			<image @click="handleClose" class="mask-close" src="/static/tqb-tabbar/close.png"
				style="width: 44rpx; height: 44rpx;"></image>
			<view class="popup-body">
				<view class="popup-item" v-for="(item, index) in handleList" :key="index" @click="handleClick(item)">
					<view class="popup-item-icon">
						<image :src="item.imageUrl" style="width: 80rpx; height: 100rpx;"></image>
					</view>
					<view class="popup-item-text">
						{{ item.text }}
					</view>
				</view>
			</view>
		</view>
		<view class="tqb-tabbar-body">
			<view class="tqb-tabbar-item" v-for="(item, index) in tabBarList" :key="index"
				@click="switchClick(item, index)">
				<view v-if="index !== 2" class="tabbar-item-body">
					<view class="tabbar-item-icon">
						<image :src="activeIndex == index ? item.selectedIconPath : item.iconPath"
							style="width: 56rpx; height: 56rpx;"></image>
					</view>
					<view :class="[activeIndex == index ? 'active' : '', 'tab-bar-text']">{{ item.text }}</view>
				</view>
				<view v-else class="add-icon">
					<image :src="item.iconPath" style="width: 88rpx; height: 88rpx;"></image>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
export default {
	props: ['activeIndex'],
	data() {
		return {
			tabBarList: [{
				text: '首页',
				iconPath: '/static/tqb-tabbar/home_off.png',
				selectedIconPath: '/static/tqb-tabbar/home_on.png',
				pagePath: '/pages/index/index',
			}, {
				text: '收藏',
				iconPath: '/static/tqb-tabbar/customer_off.png',
				selectedIconPath: '/static/tqb-tabbar/customer_on.png',
				pagePath: '/pages/index/index',
			}, {
				text: '加号',
				iconPath: '/static/tqb-tabbar/add.png',
			}, {
				text: '元宇宙',
				iconPath: '/static/tqb-tabbar/find_off.png',
				selectedIconPath: '/static/tqb-tabbar/find_on.png',
				pagePath: '/pages/index/index',
			}, {
				text: '我的',
				iconPath: '/static/tqb-tabbar/work_off.png',
				selectedIconPath: '/static/tqb-tabbar/work_on.png',
				pagePath: '/pages/index/index',
			}],
			handleList: [{
				imageUrl: '/static/tqb-tabbar/work_on.png',
				text: '添加页面',
				url: `/pages/index/index`
			}, {
				imageUrl: '/static/tqb-tabbar/work_on.png',
				text: '添加页面',
				url: `/pages/index/index`
			}],
			visible: false,
		}
	},
	watch: {
		visible: function (d) {
			// todo anything
		}
	},
	methods: {
		switchClick(data, index) {
			if (index == this.activeIndex) {
				return
			}
			if (index === 2) {
				this.visible = !this.visible;

				if (this.activeIndex == 1) {
					uni.$emit('hideCanvas', this.visible)
				}
				return
			}
			this.handleClose()
			uni.switchTab({
				url: data.pagePath
			});
		},
		handleClose() {
			this.visible = false;
			if (this.activeIndex == 1) {
				uni.$emit('hideCanvas', false)
			}
		},
		handleOpenPage(item) {
			let that = this
			setTimeout(function () {
				that.handleClose()
			}, 500)
			uni.navigateTo({
				url: item.url
			})
		},
		handleClick(item) {
			let that = this
			that.handleOpenPage(item)
			// that.$u.throttle(that.handleOpenPage(item), 500)
		}
	}
}
</script>

<style lang="scss" scoped>
.tqb-tabbar {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	z-index: 1000;
}

.tqb-tabbar-body {
	border-radius: 80rpx;
	height: 120rpx;
	display: flex;
	background: linear-gradient(to right, #ffffff, #F1EDFF);
	position: fixed;
	bottom: 68rpx;
	width: 704rpx;
	left: 24rpx;
	z-index: 1001;
	padding-top: 20rpx;
	box-shadow: 0px 0px 22rpx 9rpx rgba(0, 0, 0, 0.1);

	.tqb-tabbar-item {
		flex: 1;
		display: flex;
		justify-content: center;
		text-align: center;

		.tabbar-item-body {}

		.tabbar-item-icon {
			display: flex;
			justify-content: center;
		}

		.tab-bar-text {
			color: #999999;
			font-size: 22rpx;
			margin-top: 9rpx;
		}

		.active {
			color: #645AEE;
		}

		.add-icon {
			width: 88rpx;
			height: 88rpx;
			position: relative;
			top: -8rpx;
		}
	}
}

.tqb-tabbar-popup {
	position: fixed;
	bottom: 0;
	left: 0;
	width: 100%;
	background: linear-gradient(180deg, rgba(0, 0, 0, 0) 0%, rgba(0, 0, 0, 0.49) 32%, rgba(0, 0, 0, 0.66) 100%);
	height: 660rpx;

	.mask-close {
		position: absolute;
		top: 32rpx;
		right: 32rpx;
	}

	.popup-body {
		display: flex;
		flex-wrap: wrap;
		position: absolute;
		top: 142rpx;
		width: 100%;

		.popup-item {
			width: 20%;
			margin-bottom: 20rpx;
			text-align: center;

			.popup-item-icon {
				display: flex;
				justify-content: center;
			}

			.popup-item-text {
				font-size: 24rpx;
				color: #fff;
			}
		}
	}
}
</style>
