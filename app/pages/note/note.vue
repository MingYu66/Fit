<template>
	<gui-page :customHeader="true" :headerClass="['gui-bg-gray', 'gui-dark-bg-level-1']">
		<!-- 自定义头部导航 -->
		<template v-slot:gHeader>
			<view style="height:44px;" class="gui-flex gui-nowrap gui-rows gui-align-items-center">
				<!-- 使用组件实现返回按钮及返回首页按钮 -->
				<gui-header-leading></gui-header-leading>
				<text class="gui-h5 gui-blod gui-flex1 gui-text-center gui-ellipsis gui-primary-text">笔记</text>
				<!-- 导航文本此处也可以是其他自定义内容 -->
				<!-- 此处加一个右侧展位元素与左侧同宽，实现标题居中 -->
				<view style="width:120rpx;">
					<view class="camera-btn" @click="saveContent()">
						<image src="/static/submit.png" style="width: 50rpx;height: 50rpx" class="icon" />
					</view>
				</view>
			</view>
		</template>
		<!-- 页面主体 -->
		<template v-slot:gBody>
			<view class="container">

				<view class="header">
					<text class="timestamp" v-if="formData.id == null">{{ formatTimestamp(currentTime) }}</text>
					<text class="timestamp" v-else>{{ formData.createTime }}</text>

				</view>
			
				<view class="header">
					<input class="title-input" type="text" placeholder="标题" v-model="formData.title" 
						placeholder-class="placeholder" />
				</view>
				<!-- 富文本编辑器 -->
				<editor id="editor" class="editor" :placeholder="placeholder" @ready="onEditorReady"
					@statuschange="statusChange"></editor>

				<!-- 自定义工具栏 -->
				<view class="custom-toolbar" style="border-top: 0;margin-bottom: 0">
					<!-- 表情选择 -->
					<view class="emoji-panel" style="margin-bottom: 5px;">

						<text class="gui-footer-icon-buttons-icon gui-block-text dizhidaxue" style="font-size: 100rpx;"
							@click="insertEmoji(0,'开心')"
							:class="[(navCurrent == 0 || formData.emoji === '开心') ? 'active-color' : 'gui-nav-bottom-color']">&#xe662;</text>

						<text class="gui-footer-icon-buttons-icon gui-block-text dizhidaxue" style="font-size: 100rpx;"
							@click="insertEmoji(1,'一般')"
							:class="[(navCurrent == 1 || formData.emoji === '一般') ? 'active-color' : 'gui-nav-bottom-color']">&#xe7a0;</text>

						<text class="gui-footer-icon-buttons-icon gui-block-text dizhidaxue" style="font-size: 100rpx;"
							@click="insertEmoji(2,'不开心')"
							:class="[(navCurrent == 2 || formData.emoji === '不开心') ? 'active-color' : 'gui-nav-bottom-color']">&#xe607;</text>
					</view>

				</view>
				<view class="custom-toolbar">
					<!-- 相机按钮 -->
					<view class="camera-btn" @click="insertPhoto">
						<image src="/static/camera-icon.png" class="icon" />
					</view>
				</view>
			</view>
		</template>
	</gui-page>
</template>
</template>

<script>
	var graceJS = require('@/GraceUI5/js/grace.js');
	export default {
		data() {
			return {
				placeholder: '开始记录...',
				editorCtx: null,
				emojis: [{
						code: 'smile',
						path: '&#xe662;'
					},
					{
						code: 'neutral',
						path: '&#xe7a0;'
					},
					{
						code: 'sad',
						path: '&#xe607;'
					}
				],
				navCurrent: 0,
				formData: {
					title: '',
					content: '',
					timestamp: null,
					emoji: '开心'

				},
				currentTime: new Date(),
				isOk: false,

			}
		},
		watch: {
			isOk() { //监听isOk属性，为true时说明setContents已执行成功的回调，此时清除timer定时器
				this.isOk && clearInterval(this.timer) && uni.hideLoading()
			}
		},

		onLoad(e) {
			console.log(e)
			var _this = this
			if (e.id) {
				this.getdata(e.id)
				}
		},
		methods: {
			titlefun(){
				console.log("222")
				this.formData.title = this.formData.title
			},
			getdata(id){
				var _this = this
			uni.gRequest.post("bohe/getNote", {
					data: {
						id: id
					}
				}, (resData) => {
					
					console.log(resData)
					this.formData = resData.data.data
					this.$forceUpdate();
					console.log(this.formData)
					// this.editorCtx.setContents(this.formData.content)
					if (this.formData.emoji === '开心') {
						this.navCurrent = 0
					} else if (this.formData.emoji === '一般') {
						this.navCurrent = 1
					} else {
						this.navCurrent = 2
					}
					console.log(this.navCurrent)
					uni.createSelectorQuery().select('#editor').context((res) => {
						_this.editorCtx = res
							.context //uni.createSelectorQuery获取editor 组件对应的 editorContext实例
					}).exec()
				
			
					_this.timer = setInterval(() => { //设置timer定时器触发setContents函数
						_this.editorCtx.setContents({
							'html': this.formData.content,
							success: (res) => {
								console.log(res, "res")
								_this.isOk = true //成功的回调，说明富文本已设置内容，此时将isOk属性设为true
								_this.formData.title = resData.data.data.title
								console.log(this.formData.title)
								
							},
							fail: (err) => {
								console.log(err, "err")
							}
						})
					}, 500) //添加一个500毫秒的延迟，减少app端压力（兼容app端）
						
					setTimeout(function() {
						console.log("----")
						_this.formData.title = resData.data.data.title
						_this.titlefun()
					}, 2000);
				})
			},

			// 时间格式化方法
			formatTimestamp(date) {
				const pad = n => n.toString().padStart(2, '0')
				return `${date.getFullYear()}/${pad(date.getMonth()+1)}/${pad(date.getDate())} ${pad(date.getHours())}:${pad(date.getMinutes())}`
			},
			// 编辑器初始化完成
			onEditorReady() {
				uni.createSelectorQuery()
					.select('#editor')
					.context(res => {
						this.editorCtx = res.context
					}).exec()
			},

			// 插入照片
			async insertPhoto() {
				try {
					const res = await uni.chooseImage({
						count: 1,
						mediaType: ['image', 'video'],
						sourceType: ['album', 'camera'],
						sizeType: ['compressed']
					})

					console.log(res[1])
					if (res[1].tempFilePaths.length) {


						uni.uploadFile({
							url: 'http://192.168.3.4:8081/common/upload',
							//你的上传文件的接口地址：若依的一般就是这个
							filePath: res[1].tempFilePaths[0],
							name: 'file',

							success: (uploadFileRes) => {
								console.log(uploadFileRes)
								let data = JSON.parse(uploadFileRes.data)
								console.log(data);

								console.log(data.url);
								this.editorCtx.insertImage({
									src: data.url,
									width: '80%',
									success: () => console.log('图片插入成功')
								})
							}
						});

						// const e = await uni.gRequest.post("common/upload", {
						// 	file: res[1].tempFilePaths[0]
						// }, (resimg) => {
						// 	console.log(resimg)

						// })





					}
				} catch (error) {
					console.log(error)
					uni.showToast({
						title: '拍照失败',
						icon: 'none'
					})
				}
			},

			// 插入表情
			insertEmoji(index, emoji) {
				this.navCurrent = index
				this.formData.emoji = emoji
			},

			// 保存笔记

			saveContent() {
				this.editorCtx.getContents({
					success: res => {
						const title = this.formData.title
						const content = res.html
						const data = {
							id: this.formData.id,
							title: title,
							content: content,
							emoji: this.formData.emoji,
							userId: graceJS.getStorage("id")
						}
						// 这里添加提交到Java后端的逻辑
						console.log('保存内容:', data)


						if (data.id) {
							uni.gRequest.post("bohe/editNote", {
								data: data
							}, (resNote) => {
								console.log(resNote)
							})
						} else {
							uni.gRequest.post("bohe/addNote", {
								data: data
							}, (resNote) => {
								console.log(resNote)
							})
						}


						uni.showToast({
							title: '保存成功'
						})
						uni.navigateBack()
					},
					fail: err => {
						uni.showToast({
							title: '保存失败',
							icon: 'none'
						})
					}
				})
			},

			// 工具栏状态变化
			statusChange(e) {
				console.log('工具栏状态:', e.detail)
			},

			goBack() {
				uni.navigateBack()
			},


		}
	}
</script>

<style lang="scss">
	.header {
		display: flex;
		align-items: center;
		margin-bottom: 30rpx;
	}

	.timestamp {
		font-size: 24rpx;
		color: #999;
		min-width: 280rpx;
	}

	.title-input {
		font-size: 36rpx;
		margin-left: 30rpx;
		flex: 1;
		height: 60rpx;
		font-weight: 500;
	}

	.container {
		height: 100vh;
		display: flex;
		flex-direction: column;
	}

	.editor {
		flex: 1;
		padding: 20rpx;
		font-size: 32rpx;
		background: #fff;
	}

	.custom-toolbar {
		margin-bottom: 45px;
		height: 100rpx;
		border-top: 1rpx solid #eee;
		display: flex;
		align-items: center;
		justify-content: center;
		padding: 0 30rpx;
		background: #fff;

		.emoji-panel {
			flex: 1;
			display: flex;
			justify-content: space-around;
			gap: 20rpx;

			.emoji {
				width: 75rpx;
				height: 75rpx;
			}
		}

		.camera-btn {
			width: 80rpx;
			height: 80rpx;
			border-radius: 50%;
			background: #f0f0f0;
			display: flex;
			align-items: center;
			justify-content: center;

			.icon {
				width: 50rpx;
				height: 50rpx;
			}
		}
	}

	.active-color {
		color: #FFB347;
	}
</style>