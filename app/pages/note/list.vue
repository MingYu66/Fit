<template>
	<gui-page :customFooter="true" :footerSets="{height:150, zIndex:100, bg:'none'}" style="background-color: #f6f7fa;">
		<view slot="gBody" style="background-color: #f6f7fa;">
			<view class="" style="background-color: #f6f7fa;padding: 20rpx;height: 100%;">
				<view style="margin-top:66rpx;margin-left: 10rpx;" class="gui-padding-x">
					<text class="gui-h1 gui-color-black " style="font-weight: bold;">笔记</text>
					<view>
						<text class="gui-h6 gui-color-gray">{{list.length}}篇笔记</text>
					</view>
				</view>
				<view class="gui-bg-white gui-dark-bg-level-3 gui-padding gui-margin-top"
					style="margin: 10rpx;border-radius: 5%;">
					<navigator :url="'./note?id='+item.id" class="gui-list-items" v-for="item,index in list">
						<view class="gui-list-body gui-border-b">
							<view class="gui-list-title">
								<text class="gui-list-title-text gui-primary-text">{{item.title}}</text>
							</view>
							<text class="gui-list-body-desc gui-color-gray">{{item.createTime}}</text>
						</view>
						<text v-if="item.emoji === '开心'"
							class="gui-footer-icon-buttons-icon active-color gui-block-text dizhidaxue"
							style="font-size: 100rpx;">&#xe662;</text>
						<text v-if="item.emoji === '不开心'"
							class="gui-footer-icon-buttons-icon active-color gui-block-text dizhidaxue"
							style="font-size: 100rpx;">&#xe607;</text>
						<text v-if="item.emoji === '一般'"
							class="gui-footer-icon-buttons-icon active-color gui-block-text dizhidaxue"
							style="font-size: 100rpx;">&#xe7a0;</text>
					</navigator>

				</view>


				<uni-fab ref="fab" :pattern="pattern" :horizontal="horizontal" :content="content" :vertical="vertical"
					@fabClick="fabClick" @trigger="trigger" />


			</view>




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
	var graceJS = require('@/GraceUI5/js/grace.js');

	export default {
		data() {
			return {
				list: [],
				pattern: {
					color: '#7A7E83',
					backgroundColor: '#fff',
					selectedColor: '#007AFF',
					buttonColor: '#ffb347',
					iconColor: '#fff'
				},
				content: [{
						iconPath: '/static/add.png',
						selectedIconPath: '/static/add.png',
						// text: '相册',
						active: false
					},

					{
						iconPath: '/static/msg.png',
						selectedIconPath: '/static/msg.png',
						// text: '收藏',
						active: false
					}
				],
				horizontal: 'right',
				vertical: 'top',
			}
		},
		onLoad() {
			this.navCurrent = 1
			this.getList()

		},
		onShow() {
			this.navCurrent = 1
			this.getList()
		},
		onHide() {
			this.navCurrent = 1
			this.getList()
		},
		methods: {
			getList() {
				uni.gRequest.get("bohe/listNote", {
					data: {
						userId: graceJS.getStorage("id")
					}
				}, (res) => {
					console.log(res)
					this.list = res.rows
				})
			},

			trigger(e) {
				console.log(e)
				this.content[e.index].active = !e.item.active
				if(e.index == 0){
					uni.navigateTo({
						url:"./note"
					})
				}else{
					uni.navigateTo({
						url:"/pages/ai/ai/ai"
					})
				}
			},

			navChang: function(index) {
				console.log(index)
				if (index == 0) {
					uni.switchTab({
						url: "../switchPages/index"
					})
				} else if (index == 1) {

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
		}
	}
</script>
<style scoped>
	page {
		background-color: #f0f1f3;
	}

	.active-color {
		color: #FFB347;
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
</style>