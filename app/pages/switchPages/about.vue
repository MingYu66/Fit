<template>
	<gui-page :customHeader="true" :customFooter="true" iphoneXButtomStyle="background:#f8f8f8"
		:footerSets="{height:150, zIndex:100, bg:'none'}" style="background-color: #f6f7fa;">
		<view slot="gHeader"><gui-demo-header></gui-demo-header></view>
		<view slot="gBody" class="gui-margin " style="padding-bottom:50rpx;">
			<view class="gui-list gui-margin-top gui-padding">
				<view class="gui-list-items">
					<view class="gui-list-image ucenter-face gui-relative">
						<image class="gui-list-image ucenter-face-image"
							src="https://images.unsplash.com/photo-1553532070-2ca3b5876896?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxlZGl0b3JpYWwtZmVlZHwxNzZ8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=100&q=60"
							mode="widthFix"></image>
					</view>
					<view class="gui-list-body">
						<view class="gui-list-title">
							<text class="gui-list-title-text gui-primary-color">{{name}}</text>
						</view>
						<text
							class="gui-list-body-desc gui-color-gray gui-block-text">性别：{{sex == 2 ? '女' : '男'}}</text>
					</view>
					
				
					<!-- <text class="gui-list-arrow-right gui-icons gui-color-gray">&#xe601;</text> -->
					<gui-box-banner :items="items"></gui-box-banner>

				</view>
			</view>
			<view class="gui-padding-x" style="background-color: white;">
				<view class="gui-margin-top gui-flex gui-row gui-nowrap">
					<view class="demo gui-block gui-dark-bg-level-3 gui-color-gray">

						<view class="demo-content">{{ weight }}kg</view>
						<view>体重</view>
					</view>
					<view class="demo gui-block gui-dark-bg-level-3 gui-color-gray">

						<view class="demo-content"> {{ bmi.toFixed(2) }}</view>
						<view>BMI</view>
					</view>
					<view class="demo gui-block gui-dark-bg-level-3 gui-color-gray">

						<view class="demo-content">{{ getHealthStatus(bmi) }}</view>
						<view>状态</view>
					</view>
					

				</view>
			</view>

			<!-- <view class="charts-box">
				<qiun-data-charts type="line" :opts="opts" :chartData="chartData" />
			</view> -->



			<view class="ucenter-line"></view>
			<view class="">

				<view class="gui-list-items" @click="userInfo">
					<text class="gui-list-icon mint gui-icons gui-color-orange">&#xe640;</text>
					<view class="gui-list-body gui-border-b">
						<view class="gui-list-title">
							<text class="gui-list-title-text">个人信息</text>
						</view>
					</view>
					<text class="gui-list-arrow-right gui-icons gui-color-gray">&#xe601;</text>
				</view>
				
				
				<view class="gui-list-items" @click="myLove">
					<text class="gui-list-icon mint gui-icons gui-color-orange">&#xe633;</text>
					<view class="gui-list-body gui-border-b">
						<view class="gui-list-title">
							<text class="gui-list-title-text">我的食物</text>
						</view>
					</view>
					<text class="gui-list-arrow-right gui-icons gui-color-gray">&#xe601;</text>
				</view>
				
				<view class="gui-list-items" @click="goJianyi()">
					<text class="gui-list-icon  gui-icons gui-color-orange">&#xe67c;</text>
					<view class="gui-list-body gui-border-b">
						<view class="gui-list-title">
							<text class="gui-list-title-text">建议反馈</text>
						</view>
					</view>
					<text class="gui-list-arrow-right gui-icons gui-color-gray">&#xe601;</text>
				</view>
				<view class="gui-list-items" @click="outLogin()">
					<text class="gui-list-icon mint gui-icons gui-color-orange">&#xe60d;</text>
					<view class="gui-list-body gui-border-b">
						<view class="gui-list-title">
							<text class="gui-list-title-text">退出登录</text>
						</view>
					</view>
					<text class="gui-list-arrow-right gui-icons gui-color-gray">&#xe601;</text>
				</view>



				<!-- 	<view class="gui-flex gui-align-items-center" @click="userInfo">
					<text class="gui-list-icon gui-icons gui-color-yellow">&#xe613;</text>
					<view class="gui-text-small">
						账户设置
					</view>

				</view> -->



				<!-- 		
				<view class="gui-flex gui-align-items-center" @click="outLogin()">
					<text class="gui-list-icon mint  gui-color-yellow">&#xe651;</text>
					<view class="gui-text-small">
						体重记录
					</view>

				</view> -->

				<!-- 	<view class="gui-flex gui-align-items-center" @click="goAi(2)">
					<text class="gui-list-icon mint  gui-color-yellow">&#xe60e;</text>
					<view class="gui-text-small">
						食物识别
					</view>

				</view>

				<view class="gui-flex gui-align-items-center" @click="goAi(1)">
					<text class="gui-list-icon mint  gui-color-yellow">&#xe611;</text>
					<view class="gui-text-small">
						配料识别
					</view>

				</view> -->
			</view>
			<view class="" style="width: 100%;height: 30upx;">

			</view>
			<!-- <view class="gui-list-items gui-boo" @click="outLogin()"
				style="position: absolute; bottom: 0; text-align: center; width: 100%;margin-left: -5%;">
				<text class="gui-list-icon gui-icons gui-color-red">&#xe62e;</text>

				<text class="gui-list-title-text">退出登录</text>

			</view> -->


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
				navCurrent: 4,
				name: "花花",
				weight: null,
				height: null,
				bmi: null,
				tems: [
					[80, '', '动态'],
					[100, '', '好友'],
					[50, '', '私信'],
					['￥199', '', '余额']
				],
				weights: [],
				chartData: {},
				//您可以通过修改 config-ucharts.js 文件中下标为 ['line'] 的节点来配置全局默认参数，如都是默认参数，此处可以不传 opts 。实际应用过程中 opts 只需传入与全局默认参数中不一致的【某一个属性】即可实现同类型的图表显示不同的样式，达到页面简洁的需求。
				opts: {
					color: ["#1890FF", "#91CB74", "#FAC858", "#EE6666", "#73C0DE", "#3CA272", "#FC8452", "#9A60B4",
						"#ea7ccc"
					],
					padding: [15, 10, 0, 15],
					dataLabel: false,
					dataPointShape: false,
					enableScroll: false,
					legend: {},
					xAxis: {
						disableGrid: true,
						fontSize: 10,
						rotateLabel: true
					},
					yAxis: {
						gridType: "dash",
						dashLength: 2,
						data: [{
							min: 0,
							max: 150
						}]
					},
					extra: {
						line: {
							type: "curve",
							width: 2,
							activeType: "hollow",
							linearType: "custom",
							onShadow: true,
							animation: "horizontal"
						}
					}
				}
			}
		},
		onReady() {
			this.getServerData();
		},
		onShow() {
			this.navCurrent = 4
		},
		onLoad() {
			this.navCurrent = 4
			this.name = graceJS.getStorage("name")
			this.sex = graceJS.getStorage("sex")
			uni.hideTabBar()
			// this.calculateBMI()
			this.getServerData();
		},
		methods: {
			openPay(){
				uni.previewImage({
					urls: ["/static/add.png"],
					
				});
			},
			goJianyi() {
				uni.navigateTo({
					url: "/pages/jianyi/jianyi/jianyi"
				})
			},
			goOrders(){
				uni.navigateTo({
					url:"/pages/shop/order"
				})
			},
			getServerData() {
				uni.gRequest.post("bohe/getUserWeight", {
					data: {
						userId: graceJS.getStorage("id"),
						weight: this.kg
					}
				}, (res) => {
					console.log(res.data.data)
					var days = []
					var weight = []
					this.weight = res.data.data[0].weight
					this.calculateBMI()
					for (let s of res.data.data) {
						if (days.length < 7) {
							days.push(s.createTime)
							weight.push(s.weight)
						}
					}
					var ast = {
						categories: days,
						xAxis: {
							fontSize: 10
						},
						series: [{
								// name: "血糖记录",
								name: "体重记录",
								// 
								linearColor: [
									[
										0,
										"#1890FF"
									],
									[
										0.25,
										"#00B5FF"
									],
									[
										0.5,
										"#00D1ED"
									],
									[
										0.75,
										"#00E6BB"
									],
									[
										1,
										"#90F489"
									]
								],
								setShadow: [
									3,
									8,
									10,
									"#1890FF"
								],
								data: weight
							},

						]
					}

					this.chartData = JSON.parse(JSON.stringify(ast))


				})
			},
			calculateBMI() {
				// this.weight = graceJS.getStorage("weight")
				this.height = graceJS.getStorage("height") / 100
				console.log(this.weight, this.height)
				if (this.weight && this.height) {
					const heightSquared = this.height * this.height;
					this.bmi = this.weight / heightSquared;
				} else {
					alert('请输入有效的体重和身高');
				}
			},
			getHealthStatus(bmi) {
				if (bmi < 18.5) {
					return '过轻';
				} else if (bmi < 24.9) {
					return '正常';
				} else if (bmi < 29.9) {
					return '超重';
				} else {
					return '肥胖';
				}
			},
			goAi(e) {
				if (e == 1) {
					uni.navigateTo({
						url: "../paizhao/burden-sheet/burden-sheet"
					})
				} else {
					uni.navigateTo({
						url: "../paizhao/ai-food/ai-food"
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
			outLogin() {


				graceJS.removeStorage("id")
				uni.reLaunch({
					url: '../login/login'
				});

			},
			myLove() {
				uni.navigateTo({
					url: "../myLove/myLove"
				})
			},
			myShopLove(){
				uni.navigateTo({
					url:"/pages/shop/listLove"
				})
			},
			userInfo() {
				uni.navigateTo({
					url: "../userInfo/userInfo"
				})
			}
		}
	}
</script>
<style>
	.ucenter-face {
		width: 100rpx;
		height: 100rpx;
	}

	.ucenter-face-image {
		width: 100rpx;
		height: 100rpx;
	}

	.ucenter-line {
		height: 20rpx;
		background-color: #F6F7F8;
		margin: 16rpx 0;
	}

	.logoff {
		line-height: 88rpx;
		font-size: 28rpx;
	}

	.gui-list-title-text {
		line-height: 60rpx;
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

	/* 请根据实际需求修改父元素尺寸，组件自动识别宽高 */
	.charts-box {
		width: 100%;
		height: 300px;
	}

	.demo {
		width: 210rpx;
		height: 120rpx;
		background-color: #ffffff;
		line-height: 80rpx;
		font-size: 22rpx;
		text-align: center;
		margin: 10rpx;
		border-right: 1rpx solid #f6f7fa;
	}

	.demo1 {
		width: 210rpx;
		height: 120rpx;
		background-color: #ffffff;
		line-height: 80rpx;
		font-size: 22rpx;
		text-align: center;
		margin: 10rpx;
	}

	.demo view {
		height: 30px;
	}

	.demo1 view {
		height: 30px;
	}

	.demo-content {
		color: black;
		font-size: 36rpx;
	}
</style>