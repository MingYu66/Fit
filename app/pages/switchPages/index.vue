<template>
	<view class="">
		<gui-page :customHeader="true" :customFooter="true" iphoneXButtomStyle="background:#F8F8F8"
			:footerSets="{height:150, zIndex:100, bg:'none'}" style="background-color: #f6f7fa;">
			<view slot="gHeader"><gui-demo-header></gui-demo-header></view>

			<view slot="gBody" class="gui-margin" style="padding-bottom:50rpx; ">
				<view class="gui-margin-top gui-bg-gray search-warp gui-border-box">
					<gui-search :disabled="true" @tapme="tapme"></gui-search>
				</view>
				<!-- <view @click="gostport">
					paobu 
				</view> -->
				<view class="card-body gui-card-body gui-box-shadow gui-border-b card">
					<view class="" style="display: flex; justify-content: space-around;align-items: center;">
						<view class="btnImg" @click="goAI(3)" style="margin-left: 8px;">
							<view><text class="gui-grids-icon gui-icons dizhidaxue"
									style="font-size: 30px;margin-right: 10rpx;">&#xe603;

								</text></view>
							DeepSeek
						</view>


						<view class="btnImg" @click="goAI(2)" style="width: 30%;">
							<text class="  dizhidaxue" style="font-size: 30px;margin-left: -20rpx;">&#xe61a;</text>
							食物识别
						</view>

					</view>
				</view>
				<view class="card-body gui-card-body gui-box-shadow gui-border-b card">
					<view class="" style="display: flex; justify-content: space-around;align-items: center;">
						<view class="btnImg" @click="goAI(5)" style="margin-left: 8px;">
							<view><text class="gui-grids-icon gui-icons dizhidaxue"
									style="font-size: 30px;margin-right: 10rpx;">&#xeb81;

								</text></view>
							视频课程
						</view>



						<view class="btnImg" @click="goAI(4)">
							<view><text class="gui-grids-icon gui-icons dizhidaxue"
									style="font-size: 30px;margin-right: 10rpx;">&#xe8eb;

								</text></view>
							专注计时
						</view>
					</view>
				</view>
				
				<view class="card-body gui-card-body gui-box-shadow gui-border-b card">
					<view style="display: flex;justify-content: space-between;">

						<view
							style="color: black;margin-bottom: 10rpx;display: flex; align-items: center;color: #2db7f5"
							@click="goWeightInfo()">
							<view><text class="gui-grids-icon aknngsa" style="font-size: 20px;">&#xead8;

								</text></view>
							<!-- <view style="margin-left: 10px;">
								{{date1}}
							</view> -->
							体重记录
						</view>

						<!-- <view class="gui-grids-items" @tap="navChang(2)">
							<text class="gui-grids-icon gui-icons" style="color: #2db7f5;font-size: 45rpx;">&#xe625;
							</text>
						</view> -->
					</view>

					<view class="nav " style="display: flex;align-items:center; justify-content: center;">
						<view class="yuan_text">
							今日体重
							<view class="gui-bold">
								{{kg}}
							</view>
						</view>
						<view style="text-align: center;">
							<view class="">
								体重打卡
							</view>
							<view class="" style="margin-top: 10rpx;">
								{{(kg > 0 && dk == true) ? '今日体重：' + kg + 'KG' : '未打卡'}}
							</view>
						</view>

						<view class="yuan_text">
							BMI指数
							<view class="gui-bold">
								{{bmi.toFixed(2)}}
							</view>
						</view>
					</view>
				</view>


				<view class="card-body gui-card-body gui-box-shadow gui-border-b card">
					<view style="color: black;margin-bottom: 10rpx;display: flex; align-items: center;color: #2db7f5"
						@click="goYsInfo()">
						<view><text class="gui-grids-icon aknngsa " style="font-size: 20px;">&#xeae8;

							</text></view>
						<view>
							<!-- {{date1}} -->
							饮食记录
						</view>
					</view>
					<view class="" style="display: flex; justify-content: space-between;">

					</view>
					<view class="nav " style="display: flex;align-items:center; justify-content: center;">
						<view class="yuan_text">
							今日摄入
							<view class="gui-bold">
								{{foodNum}}
							</view>
						</view>
						<l-circle :current.sync="modelVale" :percent="percent">
							<view class="t_c" style="font-size: 26upx;color: #333; text-align: center;">
								<view>
									还可以吃
								</view>
								<view>
									<text
										class="gui-bold gui-h4">{{useCalory - caloryNum > 0 ? useCalory - caloryNum : '别吃了😭'}}</text>
								</view>
								<view style="color: #bdbdbd;">
									推荐摄入 {{useCalory}}
								</view>
							</view>
						</l-circle>

						<view class="yuan_text">
							今日消耗
							<view class="gui-bold">
								{{ydNum}}
							</view>
						</view>
					</view>

					<view style="width: 100%;height:40upx; text-align: center; color: #bdbdbd;">
						.....................................................................

					</view>
					<view
						class="gui-margin-top gui-flex gui-row gui-space-between gui-flex1 gui-align-items-center gui-margin gui-text-small">
						<view @click="add('0')" class="gui-text-center">
							<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer">&#xe767;</text>
							<view>早餐</view>
						</view>
						<view @click="add('1')" class="gui-text-center">
							<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer">&#xe600;</text>
							<view>午餐</view>
						</view>
						<view @click="add('2')" class="gui-text-center">
							<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer">&#xe691;</text>
							<view>晚餐</view>
						</view>
						<view @click="add('3')" class="gui-text-center">
							<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer">&#xe641;</text>
							<view>加餐</view>
						</view>
						<view @click="add('4')" class="gui-text-center">
							<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer">&#xe678;</text>
							<view>运动</view>
						</view>
					</view>


				</view>






				<view class="card-body gui-card-body gui-box-shadow gui-border-b card">
					<view class="">
						<view>
							<text class="gui-h5  gui-bold" style="color: #2db7f5">定制食谱</text>
						</view>
						<view class="">
							<navigator url="/pages/menu/menu-list/menu-list" class="btnImg">
								不知道吃啥？点击我吧！
							</navigator>
						</view>

					</view>
				</view>

				<view class="card-body gui-card-body gui-box-shadow gui-border-b card">
					<view class="">
						<view>
							<text class="gui-h5  gui-bold" style="color: #2db7f5">推荐食谱</text>
						</view>
						<view class="">
							<navigator url="/pages/dietPlan/dietPlan" class="btnImg">
								不知道吃啥？点击我吧！
							</navigator>
						</view>

					</view>
				</view>
				<view class="card-body gui-card-body gui-box-shadow gui-border-b card">
					<!-- <text >猜你喜欢</text> -->
					<view class="gui-margin-top"
						style="display: flex;justify-content: space-between;margin-bottom: 10rpx;">
						<text class="gui-h5  gui-bold" style="color: #2db7f5">食物推荐</text>

						<text @click="goLists" class="gui-h6 gui-bold">更多>></text>
					</view>
					<navigator v-if="recommends.length > 0" class="gui-list-items " v-for="item in recommends"
						:url="'/pages/foodInfo/foodInfo?item='+ encodeURIComponent(JSON.stringify(item))">
						<view class="gui-list-image gui-relative">
							<image class="gui-list-image" :src="item.thumbImageUrl"></image>

						</view>
						<view class="gui-list-body gui-border-b">
							<view class="gui-list-title">

								<text class="gui-list-title-text gui-primary-color">{{item.name}}</text>

							</view>

							<text class="gui-list-body-desc gui-color-gray"><text
									class="gui-color-red">{{item.calory}}</text>
								千卡/{{item.weight}}克</text>
						</view>
						<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-green"
							v-if="item.healthLight == 1" style="border-radius: 100%;width: 20upx;height:20upx;"></text>
						<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-yellow"
							v-if="item.healthLight == 2" style="border-radius: 100%;width: 20upx;height:20upx;"></text>
						<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-red"
							v-if="item.healthLight == 3" style="border-radius: 100%;width: 20upx;height:20upx;"></text>
						<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-black"
							v-if="item.healthLight == 4" style="border-radius: 100%;width: 20upx;height:20upx;"></text>
					</navigator>
					<text v-if="recommends.length ==  0"
						class=" gui-margin-top gui-h5 gui-color-gray gui-bold gui-flex gui-align-items-center gui-justify-content-center">
						我会根据你常吃的食物猜出你有可能喜欢吃的食物 </text>


				</view>






			</view>
			<view slot="gFooter" class="gui-flex gui-rows gui-nowrap gui-space-between gui-align-items-end"
				style="height:160rpx;">
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

		<gui-modal ref="guimodal1" title="删除">
			<view slot="content" class="gui-padding gui-bg-gray">
				<text class="gui-block-text gui-text-center gui-text gui-color-gray"
					style="line-height:100rpx; padding:10rpx;">您确定删除吗？</text>
			</view>
			<!-- 利用 flex 布局 可以放置多个自定义按钮哦  -->
			<view slot="btns" class="gui-flex gui-rows gui-space-between">
				<view class="modal-btns gui-flex1" style="margin-right:80rpx;">
					<text class="modal-btns gui-color-gray" @tap="close1">取消</text>
				</view>
				<view class="modal-btns gui-flex1" style="margin-left:80rpx;">
					<text class="modal-btns gui-color-blue" @tap="confirm1">确认</text>
				</view>
			</view>
		</gui-modal>

		<gui-modal ref="openWeight" title="体重打卡">
			<view slot="content" class="gui-padding gui-bg-gray">
				<view class="gui-form-item gui-border-b">
					<text class="gui-form-label">今日体重</text>
					<view class="gui-form-body">
						<!-- 						<input type="text" class="gui-form-input" v-model="weight" name="name1" placeholder="mg/dL" />
 -->
						<input type="text" class="gui-form-input" v-model="weight" name="name1" placeholder="" />

					</view>
					<text class="gui-form-label">KG</text>
				</view>
			</view>
			<!-- 利用 flex 布局 可以放置多个自定义按钮哦  -->
			<view slot="btns" class="gui-flex gui-rows gui-space-between">
				<view class="modal-btns gui-flex1" style="margin-right:80rpx;">
					<text class="modal-btns gui-color-gray" @tap="close2">稍等一下</text>
				</view>
				<view class="modal-btns gui-flex1" style="margin-left:80rpx;">
					<text class="modal-btns gui-color-blue" @tap="confirm2">提交打卡</text>
				</view>
			</view>
		</gui-modal>




	</view>

</template>
<script>
	import countDown from '@/components/zh-countDown/countDown/countDown.vue'
	var graceJS = require('@/GraceUI5/js/grace.js');
	export default {
		components: {
			countDown
		},
		data() {
			return {
				weight: 0,
				kg: 0,
				navCurrent: 0,
				useCalory: 0, //每日推荐总摄入
				calorys: [], //饮食 + 运动消耗 数据
				caloryNum: 0, // 今日摄入卡路里   饮食 - 运动消耗
				ydNum: 0, //运动消耗
				foodNum: 0, //饮食摄入
				type0: [],
				type1: [],
				type2: [],
				type3: [],
				type4: [],
				times: 0,
				modelVale: 0,
				percent: 500 / 4000 * 100,
				//分类
				categories: [],
				del: {

				},
				recommends: [],
				moves: [],
				bmi: 0,
				dk: false,
				date1: null,
				heatsData: {},
				bloodsData: {},
				sleepsData: {},
				stepsData: {},
				heart:0,
				sleep:0,
				blood:0,
				step:0,
				msg:"当前身体状况无异常，请坚持锻炼身体",
				opts: {
					dataLabel: false,
					dataPointShape: false,
					color: ["#1890FF", "#91CB74", "#FAC858", "#EE6666", "#73C0DE", "#3CA272", "#FC8452", "#9A60B4",
						"#ea7ccc"
					],
					padding: [15, 10, 0, 15],
					enableScroll: false,
					legend: {},
					xAxis: {
						rotateLabel: true,
						rotateAngle: 45,
						disableGrid: true,
						// fontSize:5,
						labelCount: 15
					},
					yAxis: {
						gridType: "dash",
						dashLength: 2
					},
					extra: {
						line: {
							animation: "vertical",
							type: "straight",
							width: 2,
							activeType: "hollow"
						}
					}
				},
			}
		},
		onShow() {
			// this.getUserCalory()
			this.navCurrent = 0
		},
		onReady() {
			// this.getheathData()
		},
		onLoad() {

			this.navCurrent = 0
			var date = new Date();

			this.date1 = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()

			uni.hideTabBar()
			this.getUserCalory()
			uni.startPullDownRefresh();
			// this.getCategorList()


		},
		onPullDownRefresh() {
				console.log('refresh');
				this.getUserCalory()
				setTimeout(function () {
					uni.stopPullDownRefresh();
				}, 1000);
			},
		methods: {
			goLists() {
				uni.navigateTo({
					url: "../list/food-lists/food-lists"
				})
			},
			goYsInfo() {
				uni.navigateTo({
					url: "../info/info/info"
				})
			},
			goWeightInfo() {
				uni.navigateTo({
					url: "../info/weight/weight"
				})
			},
			goAI(e) {
				if (e == 1) {
					uni.navigateTo({
						url: "../paizhao/burden-sheet/burden-sheet"
					})
				} else if (e == 2) {
					uni.navigateTo({
						url: "../paizhao/ai-food/ai-food"
					})
				} else if (e == 3) {
					uni.navigateTo({
						url: "../ai/ai/ai"
					})
				} else if (e == 4) {
					uni.navigateTo({
						url: "../zhuanzhu/zhuanzhu"
					})
				} else if (e == 5) {
					uni.navigateTo({
						url: "../xinli/xinli"
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

					this.$refs.openWeight.open();

				
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
			goShop() {
				uni.navigateTo({
					url: "/pages/shop/list"
				})
			},
			//BMI计算
			calculateBMI() {
				// this.weight = graceJS.getStorage("weight")
				this.height = graceJS.getStorage("height") / 100
				console.log(this.kg, this.height)
				if (this.kg && this.height) {
					const heightSquared = this.height * this.height;
					this.bmi = this.kg / heightSquared;
				} else {
					alert('请输入有效的体重和身高');
				}
			},
			// 监听点击
			tapme: function() {
				uni.navigateTo({
					url: '../c-list/c-list',
					success: res => {},
					fail: () => {},
					complete: () => {}
				});
			},
			//挂件
			pendantTap: function() {
				uni.showToast({
					icon: "none",
					title: "挂件点击"
				});
			},
			getUserCalory() {
				this.useCalory = graceJS.getStorage("calory")
				var date = new Date();
				var params = {
					uid: graceJS.getStorage("id"),
					createTime: date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
				}
				uni.gRequest.post("bohe/recommendLoveFood", {
					data: {
						uid: graceJS.getStorage("id")
					}
				}, (res) => {
					try {
						console.log(JSON.parse(res.data.msg))

						this.recommends = JSON.parse(res.data.msg)
					} catch (e) {
						this.recommends = []
					}
				})

				uni.gRequest.post("bohe/recommendMove", {
					data: {
						uid: graceJS.getStorage("id")
					}
				}, (res) => {
					try {
						console.log("===========")
						console.log(JSON.parse(res.data.msg))

						this.moves = JSON.parse(res.data.msg)
					} catch (e) {
						this.moves = []
					}
				})



				uni.gRequest.post("bohe/calories", {
					data: params
				}, (res) => {
					console.log(res.data)
					if (res.data.code == 200) {

						this.calorys = res.data.data
						this.caloryNum = 0
						this.ydNum = 0
						this.foodNum = 0
						var type0 = []
						var type1 = []
						var type2 = []
						var type3 = []
						var type4 = []

						console.log(this.calorys)
						for (let i = 0; i < this.calorys.length;) {

							console.log(this.calorys[i])
							if (this.calorys[i].cType == 4) {
								type4.push(this.calorys[i])
								this.ydNum = this.ydNum + this.calorys[i].cValue
								this.caloryNum = this.caloryNum - this.calorys[i].cValue
							} else {
								switch (this.calorys[i].cType) {
									case 0:
										type0.push(this.calorys[i])
										break;
									case 1:
										type1.push(this.calorys[i])
										break;
									case 2:
										type2.push(this.calorys[i])
										break;
									case 3:
										type3.push(this.calorys[i])
										break;
									default:
										break;
								}
								this.foodNum = this.foodNum + this.calorys[i].cValue
								this.caloryNum = this.caloryNum + this.calorys[i].cValue
							}
							i++;
						}
						this.type0 = type0
						this.type1 = type1
						this.type2 = type2
						this.type3 = type3
						this.type4 = type4
						this.caloryNum = parseInt(this.caloryNum)
						this.ydNum = parseInt(this.ydNum)
						this.foodNum = parseInt(this.foodNum)
						this.useCalory = parseInt(this.useCalory)

						this.percent = (this.caloryNum / (this.useCalory)).toFixed(2) * 100
						console.log(this.percent)
					}

				})
				this.getWeight()


			},
			getWeight() {
				uni.gRequest.post("bohe/getDayWeight", {
					data: {
						userId: graceJS.getStorage("id")
					}
				}, (res) => {
					console.log(res.data.data)
					if (res.data.data.length > 0) {
						console.log("更新打卡")
						this.kg = res.data.data[0].weight
						this.calculateBMI()
						this.dk = true
					} else {
						uni.gRequest.post("bohe/getUserWeight", {
							data: {
								userId: graceJS.getStorage("id"),
							}
						}, (resList) => {
							console.log(resList)
							this.kg = resList.data.data[0].weight
							this.calculateBMI()
						})
						this.dk = false
					}

				})
			},
			// //获取分类数据
			// getCategorList() {
			// 	uni.gRequest.post("bohe/categorisList", {}, (res) => {
			// 		console.log(res.data)
			// 		if (res.data.code == 200) {
			// 			this.categories = res.data.data
			// 		}

			// 	})
			// },
			add(e) {
				// alert(e)
				console.log(e)
				if (e != 4) {

					graceJS.navigate("../c-list/c-list?type=" + e)
				} else {
					graceJS.navigate("../c-move-list/c-move-list")
				}

			},
			//分类列表/*  */
			getCategorise(e) {
				// alert(e)

				graceJS.navigate('../list/list?id=' + e);
			},
			//打开删除弹窗
			open1: function(e) {
				this.del = e
				this.$refs.guimodal1.open();
			},
			//关闭删除弹窗
			close1: function() {
				this.$refs.guimodal1.close();
			},
			//关闭删除弹窗
			close2: function() {
				this.$refs.openWeight.close();
			},
			confirm2: function() {
				this.kg = this.weight
				this.$refs.openWeight.close();
				this.dk = true

				uni.gRequest.post("bohe/setUserWeight", {
					data: {
						userId: graceJS.getStorage("id"),
						weight: this.kg
					}
				}, (res) => {
					graceJS.setStorage("weight", this.kg)
					console.log(res.data)
					uni.showModal({
						content: res.data.msg
					})
					this.getWeight()
				})



			},

			// 确定删除
			confirm1: function() {

				uni.gRequest.post("bohe/delCalories", {
					data: this.del
				}, (res) => {
					console.log(res.data)
					if (res.data.code == 200) {

						this.getUserCalory()
						this.$refs.guimodal1.close();
						uni.showToast({
							title: "删除成功",
							icon: "success",

						})
					}

				})


			},

			formattedTime(time) {
				// 提取小时和分钟
				const timePart = time.split(' ')[1]; // 获取 "20:32:00"
				return timePart.slice(0, 5); // 截取前5个字符 → "20:32"
			},
			getheathData() {
				console.log("请求身体数据===")
				uni.gRequest.get("bohe/gethealthDatas", {

				}, (res) => {
					console.log(res)
					console.log("222请求身体数据2===")
					let strHeart = {
						categories: ["2018", "2019", "2020", "2021", "2022", "2023"],
						series: [{
							name: "心率",
							data: [35, 8, 25, 37, 4, 20]
						}]
					};
					let strsleep = {
						categories: [],
						series: [{
							name: "睡眠",
							data: []
						}]
					};
					let strstep = {
						categories: [],
						series: [{
							name: "步数",
							data: []
						}]
					};
					let strblood = {
						categories: [],
						series: [{
								name: "低压",
								data: []
							},
							{
								name: "高压",
								data: []
							}
						]
					};
					let date = []
					let hearts = []
					let step = []
					let sleep = []
					let sleepdate = []
					let diastolic = []
					let systolic = []
					for (let item of res.data.hearts) {
						date.push(this.formattedTime(item.timestamp))
						hearts.push(item.value)
					}
					for (let item of res.data.sleeps) {
						sleep.push(item.value)
						sleepdate.push(this.formattedTime(item.timestamp))
					}
					for (let item of res.data.steps) {
						step.push(item.value)
					}
					for (let item of res.data.bloods) {
						diastolic.push(item.diastolic)
						systolic.push(item.systolic)
					}

					strHeart.categories = date
					strHeart.series[0].data = hearts
					strsleep.categories = sleepdate
					strsleep.series[0].data = sleep
					strstep.categories = date
					strstep.series[0].data = step
					strblood.categories = date
					strblood.series[0].data = diastolic
					strblood.series[1].data = systolic
					this.heatsData = JSON.parse(JSON.stringify(strHeart))
					this.bloodsData = JSON.parse(JSON.stringify(strblood))
					this.sleepsData = JSON.parse(JSON.stringify(strsleep))
					this.stepsData = JSON.parse(JSON.stringify(strstep))
					this.heart = res.data.hearts[res.data.hearts.length -1].value
					this.sleep = res.data.sleeps[res.data.sleeps.length -1].value
					this.step = res.data.steps[res.data.steps.length -1].value
					this.blood = res.data.bloods[res.data.bloods.length -1].systolic + " - " +res.data.bloods[res.data.bloods.length -1].diastolic
					this.msg = res.data.msg
					console.log(res.data)
				})
			},
			gostport(){
				uni.navigateTo({
					url:"/pages/sprots/move/move"
				})
			}
		},
		
	}
</script>
<style scoped>
	.card {
		border-radius: 10px;
		box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
		padding: 20px;
	}


	/* 一行4格演示 */
	.gui-grids4 {
		width: 172rpx;
	}

	.card-body {
		margin-top: 50upx;
		padding: 20upx;
		background-color: white;
		/* border: 1upx solid red; */
	}

	.t_c view {
		margin-top: 5upx;
	}

	.yuan_text {
		width: 30%;
		text-align: center;
	}

	.nav {
		margin: 20upx 0 20upx 0;
	}

	.btna {
		width: 160upx;
		height: 64upx;
		background: #06D368;
		border-radius: 50upx;
		display: flex;
		justify-content: center;
		align-items: center;


	}

	.btn {
		display: flex;
		align-items: center;
		justify-content: space-around;


	}

	.btn_name {
		font-size: 32upx;
		font-weight: 600;
		color: #FFFFFF;
	}

	.gui-card-view {
		background-color: #FFFFFF;
		padding: 25rpx;
	}

	.gui-card-body {
		padding-bottom: 25rpx;
	}

	.gui-card-img {
		width: 100rpx;
		height: 100rpx;
		border-radius: 80rpx;
	}

	.gui-card-desc {
		width: 400rpx;
		margin-left: 25rpx;
		flex: 1;
	}

	.gui-card-name {
		font-size: 28rpx;
		color: #333333;
		line-height: 40rpx;
		margin-right: 20rpx;
	}

	.gui-card-text {
		line-height: 40rpx;
		font-size: 24rpx;
		color: #888888;
	}

	.gui-card-footer {
		margin-top: 25rpx;
	}

	.gui-card-footer-item {
		width: 100rpx;
		text-align: center;
		flex: 1;
		line-height: 38rpx;
		font-size: 26rpx;
		color: #666666;
	}

	/* #ifndef APP-NVUE */
	.gui-card-footer-item {
		display: block;
	}

	.gui-box-shadow {
		box-shadow: 0px 0px 3rpx #999999;
	}

	.modal-btns {
		line-height: 88rpx;
		font-size: 26rpx;
		text-align: center;
		width: 200rpx;
	}

	.pendant {
		width: 88rpx;
		height: 88rpx;
		border-radius: 88rpx;
		text-align: center;
		line-height: 88rpx;
		font-size: 55rpx;
	}

	/* 搜索组件 */

	.search-warp {
		width: 98%;
		padding: 5rpx 0rpx 0rpx 0rpx;
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

	.btnImg {
		display: flex;
		justify-content: space-around;
		align-items: center;
	}

	.chi-list {
		display: none;
	}

	/* 请根据实际需求修改父元素尺寸，组件自动识别宽高 */
	.charts-box {
		width: 100%;
		height: 300px;
		background-color: white;
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