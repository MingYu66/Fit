<template>
	<gui-page :customHeader="true" :customFooter="true" iphoneXButtomStyle="background:#F8F8F8"
		:footerSets="{height:150, zIndex:100, bg:'none'}">
		<view slot="gHeader"><gui-demo-header></gui-demo-header></view>
		<view slot="gBody" class="" style="padding-bottom:50rpx;">
			<view class="gui-margin-top">
				<!-- <gui-swiper :swiperItems="swiperItems" imgMode="scaleToFill" :width="750" :height="330"></gui-swiper> -->
				<gui-swiper v-if="type==0" :spacing="0" :padding="0" :swiperItems="swiperItems0" imgMode="scaleToFill"
					:width="750" :height="330"></gui-swiper>
				<gui-swiper v-if="type==1" :spacing="0" :padding="0" :swiperItems="swiperItems1" imgMode="scaleToFill"
					:width="750" :height="330"></gui-swiper>
				<gui-swiper v-if="type==2" :spacing="0" :padding="0" :swiperItems="swiperItems2" imgMode="scaleToFill"
					:width="750" :height="330"></gui-swiper>
			</view>
			<gui-switch-navigation :items="navItems" :isCenter="true" :size="200" lineHeight="80rpx" textAlign="center"
				activeLineWidth="200rpx" activeLineHeight="2rpx" :margin="10"
				@change="navChange"></gui-switch-navigation>
			<view class="gui-margin">
				<view>
					<!-- <button type="default" @click="open1()"
						class="gui-button-mini gui-gtbg-red gui-flex gui-align-items-center gui-justify-content-center"
						style="width:282rpx;">
						<text class="gui-color-white gui-button-text-mini gui-icons">&#xe642; 发个帖子试试</text>
					</button> -->
				</view>
				<view class="gui-margin-top">
					<text class="gui-h5 gui-color-gray gui-bold">健康社区</text>
				</view>
				<view class="gui-comments">
					<view class="gui-comments-items gui-flex gui-rows gui-nowrap gui-space-between"
						v-for="(item, index) in commentContents" :key="index">
						<!-- <avatar :username="item.name" :src="item.name" :style="'width:40px;height:40px'" :background-color="extractColorByName(item.name)" color="#fff" style="vertical-align: middle;" :inline="true" /> -->
						<!-- <image :src="avatarUrl(item.name)" mode="aspectFill" class="avatar" /> -->

						<image :src="item.avatar" class="avatar" />
						<!-- 隐藏的 Canvas 组件，不能省略 -->
						<!-- <canvas :canvas-id="'canvas' + index" class="avatar-canvas"></canvas> -->
						<canvas :canvas-id="'canvas' + index" class="avatar-canvas"></canvas>

						<view class="gui-comments-body">
							<view class="gui-flex gui-rows gui-nowrap gui-space-between gui-align-items-center">
								<text class="gui-comments-header-text gui-text gui-primary-color">{{item.name}}</text>
								<text @click="love(index)"
									class="gui-comments-header-text gui-icons gui-color-gray gui-text-small"
									:class="[item.status ? 'gui-color-blue' : '']">&#xe6ea; {{item.count}}</text>
							</view>
							<text class="gui-comments-content gui-block-text">{{item.content}}</text>
							<view v-if="item.imgs != '' && item.imgs != null" @click="preview(item.imgs)">
								<gui-image  :src="item.imgs" borderRadius="10rpx" ></gui-image>
							</view>
							<view v-if="userid == item.uid" @click="deleSpack(item.id)"
								class="gui-comments-info gui-flex gui-rows gui-nowrap gui-space-between gui-align-items-center">
								<text class="gui-comments-replay-btn gui-block-text gui-bg-gray"> </text>
								<text class="gui-comments-info-text">删除</text>
							</view>
							<view
								class="gui-comments-info gui-flex gui-rows gui-nowrap gui-space-between gui-align-items-center">
								<text class="gui-comments-replay-btn gui-block-text gui-bg-gray"> </text>
								<text class="gui-comments-info-text">{{item.createTime}}</text>
							</view>
						</view>
					</view>
				</view>

				<gui-modal style="height: 80%;"
					:customClass="['gui-bg-white', 'gui-dark-bg-level-3', 'gui-border-radius']" ref="guimodal1"
					title="发个帖子">
					<template v-slot:content>
						<view class="gui-padding gui-bg-gray gui-dark-bg-level-2">

							<view class="gui-bg-white gui-dark-bg-level-3 gui-padding gui-margin-top" style="height: 350rpx;">
								<textarea class="gui-textarea" style="width:100%;height:200rpx ;"  v-model="textareaVal" maxlength="-1"
									placeholder="说点什么吧"></textarea>
								<image :src="imgs" style="width: 150rpx;height: 150rpx" class="icon" />
							</view>
							
						

							<view class="custom-toolbar">
								<!-- 相机按钮 -->
								<view class="camera-btn" @click="insertPhoto">
									<image src="/static/camera-icon.png" style="width: 80rpx;height: 80rpx" class="icon" />
								</view>
							</view>
						</view>

					</template>

					<!-- 利用 flex 布局 可以放置多个自定义按钮哦  -->
					<template v-slot:btns>
						<view class="gui-flex gui-row gui-space-between">
							<view hover-class="gui-tap" class="modal-btns gui-flex1" style="margin-right:80rpx;">
								<text class="modal-btns gui-color-gray" @tap="close1">取消</text>
							</view>
							<view hover-class="gui-tap" class="modal-btns gui-flex1" style="margin-left:80rpx;">
								<text class="modal-btns gui-primary-color" @tap="confirm1">确认</text>
							</view>
						</view>
					</template>
				</gui-modal>
			</view>

			<uni-fab ref="fab" :pattern="pattern" :horizontal="horizontal" :content="content" :vertical="vertical"
				@fabClick="open1" />

		</view>



		<view slot="gFooter" class="gui-flex gui-rows gui-nowrap gui-space-between gui-align-items-end"
			style="height:150rpx;">
			<view class="gui-footer-icon-buttons" @tap="navChang(0)">
				<text class="gui-footer-icon-buttons-icon gui-block-text gui-icons"
					:class="[navCurrent == 0 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">&#xe63b;</text>
				<text class="gui-footer-icon-buttons-text gui-block-text gui-nav-bottom-color"
					:class="[navCurrent == 0 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">首页</text>
			</view>
			<view class="gui-footer-icon-buttons" @tap="navChang(1)">
				<text class="gui-footer-icon-buttons-icon gui-block-text dizhidaxue"
					:class="[navCurrent == 1 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">&#xe6f3;</text>
				<text class="gui-footer-icon-buttons-text gui-block-text gui-nav-bottom-color"
					:class="[navCurrent == 1 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">笔记</text>
			</view>
			<!-- 凸出按钮占位  普通按钮 实现下面的 gui-footer-icon-buttons 即可 -->
			<view class="gui-footer-icon-buttons"></view>
			<view class="gui-footer-icon-buttons" @tap="navChang(3)">
				<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer"
					:class="[navCurrent == 3 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">&#xe629;</text>
				<text class="gui-footer-icon-buttons-text gui-block-text"
					:class="[navCurrent == 3 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">交流</text>
			</view>
			<view class="gui-footer-icon-buttons" @tap="navChang(4)">
				<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer"
					:class="[navCurrent == 4 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">&#xe606;</text>
				<text class="gui-footer-icon-buttons-text gui-block-text"
					:class="[navCurrent == 4 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">我的</text>
			</view>
			<!-- 使用定位方式实现凸出按钮 -->
			<view class="gui-footer-buttons-ab" @tap="navChang(2)">
				<text class="gui-footer-buttons-ab-icon gui-block-text   zzduoer"
					:class="[navCurrent == 2 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">&#xe654;</text>
				<text class="gui-footer-icon-buttons-text gui-block-text"
					:class="[navCurrent == 2 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">打卡</text>
			</view>
		</view>
	</gui-page>
</template>
<script>
	// import Avatar from 'vue-avatar';

	var Avatar = require('vue-avatar')
	var graceJS = require('@/GraceUI5/js/grace.js');
	var request = require('@/GraceUI5/js/request/request.js');
	var graceChecker = require("@/GraceUI5/js/checker.js");
	var face =
		"https://images.unsplash.com/photo-1663717249250-804cb861ed74?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw5M3x8fGVufDB8fHx8&auto=format&fit=crop&w=100&q=80";
	var img =
		"https://images.unsplash.com/photo-1657310217094-2214abf4fd88?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxlZGl0b3JpYWwtZmVlZHwxNTN8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=600&q=60";
	export default {
		data() {
			return {
				swiperItems0: [{
						img: img,
					},
					{
						img: img,
					},
					{
						img: img,
					},
				],
				swiperItems1: [{
						img: img,
					},
					{
						img: img,
					},
					{
						img: img,
					},
				],
				swiperItems2: [{
						img: '/static/shudong.jpg',
					},
					{
						img: '/static/shudong.jpg',
					},
					{
						img: '/static/shudong.jpg',
					},
				],
				horizontal: 'right',
				vertical: 'bottom',
				pattern: {
					color: '#7A7E83',
					backgroundColor: '#fff',
					selectedColor: '#007AFF',
					buttonColor: '#ffb347',
					iconColor: '#fff'
				},
				navItems: [{
					type: 0,
					name: '饮食分享'
				}, {
					id: 1,
					name: '锻炼分享'
				}, {
					id: 2,
					name: '情感树洞'
				}],
				type: 0,
				navCurrent: 3,
				textareaVal: "", // 文本内容
				imgs:"",
				maxWords: 100, // 最多字符数
				textareatimer: null, // 延迟记录
				face: face,
				// 模拟评论数据 (实际项目来自api请求)
				commentContents: [],
				name: '张三',
				userid: 0,
				avatarCache: new Map() // 缓存头像，避免重复生成

			}
		},
		components: {
			'avatar': Avatar.Avatar
		},
		watch: {
			commentContents: {
				handler(newUsers) {
					newUsers.forEach((user, index) => {
						this.generateAvatar(user.name, index);
					});
				},
				deep: true, // 监听数组内部变化
				immediate: true // 初始化时执行
			}
		},
		onShow() {
			this.navCurrent = 3
		},
		onLoad() {
			this.navCurrent = 3
			this.userid = graceJS.getStorage("id")
			console.log(this.userid)
			this.getlist()
			uni.hideTabBar()
			// 昵称 / id / 时间 / 赞 / 内容/
			// id , uid 
		},
		methods: {
			preview(e){
				console.log(e)
				// 预览图片
						uni.previewImage({
							urls: [e],
							
						});
			},
			// 插入照片
			async insertPhoto() {
				try {
					const res = await uni.chooseImage({
						count: 1,
						sizeType: ['compressed'],
						mediaType: ['image','video'],
						sourceType: ['album', 'camera'],
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
								this.imgs = data.url
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
			navChange(e) {
				this.type = e,
					this.getlist()
			},
			generateAvatar(name, index) {
				const canvasId = `canvas${index}`;
				console.log(`检查 canvasId: ${canvasId}`);
				console.log(`开始生成头像: ${name} (索引: ${index})`);

				// **检查缓存**
				if (this.avatarCache.has(name)) {
					console.log(`缓存命中 ✅: ${name}`);
					this.$set(this.commentContents[index], "avatar", this.avatarCache.get(name));
					return;
				}
				this.$nextTick(() => {
					const ctx = uni.createCanvasContext(canvasId, this);
					const size = 50
					const radius = size / 2;

					const bgColor = this.getColorFromName(name);


					// 开始绘制

					ctx.beginPath();
					ctx.arc(radius, radius, radius, 0, 2 * Math.PI); // 画圆
					ctx.clip(); // **裁剪成圆形**
					// **绘制背景**
					ctx.setFillStyle(bgColor);
					ctx.fillRect(0, 0, size, size);

					// **绘制文字**
					ctx.setFillStyle("#ffffff");
					ctx.setFontSize(20);
					ctx.setTextAlign("center");
					ctx.setTextBaseline(
						"middle");
					ctx.fillText(name.charAt(0).toUpperCase(), size / 2, size / 2);

					console.log("Canvas 绘制中...");

					// **确保 `draw()` 执行**
					ctx.draw(true, () => {
						console.log("Canvas 绘制完成，转换图片...");
						this.convertCanvasToImage(canvasId, name, index);

						// **转换为图片**
						uni.canvasToTempFilePath({
							canvasId,
							success: (res) => {
								console.log(`头像生成成功 ??: ${name}`, res.tempFilePath);
								this.$set(this.commentContents[index], "avatar", res
									.tempFilePath);
								this.avatarCache.set(name, res.tempFilePath); // 缓存
							},
							fail: (err) => {
								console.error("canvasToTempFilePath 失败 ❌", err);
							}
						}, );
					});
				});


			},
			getColorFromName(name) {
				const colors = ["#FF5733", "#33FF57", "#3357FF", "#FF33A1", "#FFD700"];
				let hash = 0;
				for (let i = 0; i < name.length; i++) {
					hash = name.charCodeAt(i) + ((hash << 5) - hash);
				}
				console.log(colors[Math.abs(hash % colors.length)])
				return colors[Math.abs(hash % colors.length)];
			},

			deleSpack(e) {
				request.post("bohe/deleSpack", {
					data: {
						id: e,
						type: this.type
					}
				}, (res) => {
					this.getlist()
				})
			},
			navChang: function(index) {
				console.log(index)
				if (index == 0) {
					uni.switchTab({
						url: "../switchPages/index"
					})
				} else if (index == 1) {
					uni.navigateTo({
						url: "../note/list"
					})
				} else if (index == 2) {
					uni.switchTab({
						url: "../switchPages/index"
					})
				} else if (index == 3) {
					uni.switchTab({
						url: "../uSpeak/uSpeak",
						fail(err) {
							console.log(err)
						}
					})
				} else if (index == 4) {
					uni.switchTab({
						url: "../switchPages/about"
					})
				}
				this.navCurrent = index;

			},
			getlist() {
				this.userid = graceJS.getStorage("id")
				this.username = graceJS.getStorage("name")
				var formData = {
					userid: this.userid,
					type: this.type
				}
				var that = this
				/**
				 * 获取列表
				 */
				request.post("bohe/Articelist", {
					data: formData
				}, (res) => {
					console.log(res.data)
					that.commentContents = res.data
					that.commentContents.forEach((user, index) => {
						that.generateAvatar(user.name, index);
					});
					setTimeout(() => {
						uni.createSelectorQuery()
							.selectAll("canvas")
							.boundingClientRect((res) => {
								console.log("所有 Canvas:", res);
							})
							.exec();
					}, 1000);
					console.log(that.commentContents)
				})
			},
			//传入名字,根据名字生成颜色,这样颜色就固定下来了
			extractColorByName(name) {
				var temp = [];
				temp.push("#");
				for (let index = 0; index < name.length; index++) {
					temp.push(parseInt(name[index].charCodeAt(0), 10).toString(16));
				}
				return temp.slice(0, 5).join('').slice(0, 4);
			},


			open1: function() {
				this.$refs.guimodal1.open();
			},
			close1: function() {
				this.$refs.guimodal1.close();
			},
			confirm1: function() {
				console.log(this.textareaVal)
				var data = {
					uid: this.userid,
					content: this.textareaVal,
					name: this.username,
					imgs:this.imgs,
					type: this.type
				}
				request.post("bohe/bhArticeadd", {
					data: data
				}, (res) => {
					console.log(res.data)
					if (res.data.code == 200) {
						this.getlist()
						// 客户点击确认按钮后的逻辑请在此处实现
						this.$refs.guimodal1.close();
					}

				})

			},
			love(i) {
				var that = this
				this.commentContents[i]
				var list = this.commentContents
				var data = {
					uid: parseInt(this.userid),
					aid: this.commentContents[i].id
				}
				console.log(data)

				request.post("bohe/bhALoveadd", {
					data: data
				}, (res) => {
					console.log(res.data)
					if (res.data.code == 200) {
						list[i] = res.data.data
					}

					that.commentContents = list
					that.$forceUpdate();
				})

				console.log(that.commentContents)
			},

		}
	}
</script>
<style scoped>
	.modal-btns {
		line-height: 88rpx;
		font-size: 26rpx;
		text-align: center;
		width: 200rpx;
	}

	.gui-footer-icon-buttons {
		width: 130rpx;
		height: 80rpx;
		padding: 10rpx;
		flex: 1;
		position: relative;
		margin: 0;
		background-color: #F8F8F8;
	}

	.gui-footer-buttons-ab {
		position: absolute;
		z-index: 101;
		left: 325rpx;
		top: 0rpx;
		width: 110rpx;
		height: 150rpx;
	}

	.gui-footer-buttons-ab-icon {
		width: 110rpx;
		height: 110rpx;
		line-height: 120rpx;
		text-align: center;
		font-size: 68rpx;
		background-color: #F7F8FA;
		border-radius: 100rpx;
	}

	.avatar-list {
		display: flex;
		gap: 10px;
	}

	.avatar-container {
		position: relative;
		width: 100rpx;
		height: 100rpx;
	}

	.avatar {
		width: 100rpx;
		height: 100rpx;
		border-radius: 50%;
	}

	.avatar-canvas {
		width: 100rpx;
		height: 100rpx;
		position: absolute;
		/* top: -9999px; */
		/* 隐藏 Canvas */
	}
	
	
	.custom-toolbar {
		/* margin-bottom: 45px; */
		height: 100rpx;
		border-top: 1rpx solid #eee;
		display: flex;
		align-items: center;
		justify-content: center;
		padding: 0 30rpx;
		background: #fff;
	

	
	
	}
	.camera-btn {
		width: 80rpx;
		height: 80rpx;
		border-radius: 50%;
		background: #f0f0f0;
		display: flex;
		align-items: center;
		justify-content: center;
		
	
	}
	.camera-btn icon {
		width: 50rpx;
		height: 50rpx;
	}
</style>