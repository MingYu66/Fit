<template>
	<view class="">
		<gui-page :customHeader="false" iphoneXButtomStyle="background:#F8F8F8"
			:footerSets="{height:100, zIndex:100, bg:'none'}" style="background-color: #f6f7fa;">
			<view slot="gHeader"><gui-demo-header></gui-demo-header></view>

			<view slot="gBody" class="gui-margin" style="padding-bottom:50rpx; ">
				
				<view class="card-body gui-card-body gui-box-shadow gui-border-b card">
					<view style="color: #2db7f5;margin-bottom: 10rpx;display: flex; align-items: center;"
						@tap="openCalendar1">
						<view><text class="gui-grids-icon gui-icons" style="font-size: 20px;">&#xe661;
							</text></view>
						<view style="margin-left: 10px;">
							<input type="text" class="gui-form-input" :disabled="true" v-model="date1" name="name1"
								placeholder="请选择日期" />
						</view>
					</view>
					<view class="" style="display: flex; justify-content: space-between;">
						<view class="">
							饮食&运动记录
						</view>

						<view class="">
							{{kg > 0 ? '今体重日：' + kg + 'KG' : '未打卡'}}
						</view>

					</view>
					<view class="nav " style="display: flex;align-items:center; justify-content: center;">
						<view class="yuan_text">
							饮食摄入
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
										class="gui-bold gui-h4">{{useCalory - caloryNum > 0 ? useCalory - caloryNum : '别吃了??'}}</text>
								</view>
								<view style="color: #bdbdbd;">
									推荐预算 {{useCalory}}
								</view>
							</view>
						</l-circle>

						<view class="yuan_text">
							运动消耗
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








				<view class=" card gui-bg-white gui-margin-top">
					<view class="gui-flex gui-space-between gui-border-b">


						<view class="gui-text-center gui-flex">
							<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer">&#xe767;</text>
							<view>早餐</view>
						</view>
						<view class="" @click="add('0')" style="font-size: 30px;">
							+
						</view>

					</view>


					<view class="">
						<view class="gui-list-items" v-for="item in type0">
							<view class="gui-list-image gui-relative">
								<image class="gui-list-image" :src="item.cImg"></image>

							</view>
							<view class="gui-list-body gui-border-b">
								<view class="gui-list-title">

									<text class="gui-list-title-text gui-primary-color">{{item.cName}}</text>

								</view>

								<text class="gui-list-body-desc gui-color-gray"><text
										class="gui-color-red">{{item.cNum}}</text>
									克</text>
							</view>
							<view class="gui-flex gui-align-items-center" @tap="open1(item)">
								<view class=" gui-color-gray">
									{{item.cValue}}
								</view>
								<view class="" style="margin-left: 10upx;">


									<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer">&#xe60d;</text>
								</view>
							</view>


						</view>
					</view>
				</view>

				<view class=" card gui-bg-white gui-margin-top">
					<view class="gui-flex gui-space-between gui-border-b">


						<view class="gui-text-center gui-flex">
							<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer">&#xe600;</text>
							<view>午餐</view>
						</view>
						<view class="" @click="add('1')" style="font-size: 30px;">
							+
						</view>

					</view>


					<view class="">
						<view class="gui-list-items" v-for="item in type1">
							<view class="gui-list-image gui-relative">
								<image class="gui-list-image" :src="item.cImg"></image>

							</view>
							<view class="gui-list-body gui-border-b">
								<view class="gui-list-title">

									<text class="gui-list-title-text gui-primary-color">{{item.cName}}</text>

								</view>

								<text class="gui-list-body-desc gui-color-gray"><text
										class="gui-color-red">{{item.cNum}}</text>
									克</text>
							</view>
							<view class="gui-flex gui-align-items-center" @tap="open1(item)">
								<view class=" gui-color-gray">
									{{item.cValue}}
								</view>
								<view class="" style="margin-left: 10upx;">


									<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer">&#xe641;</text>
								</view>
							</view>


						</view>
					</view>
				</view>

				<view class=" card gui-bg-white gui-margin-top">
					<view class="gui-flex gui-space-between gui-border-b">


						<view class="gui-text-center gui-flex">
							<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer">&#xe691;</text>
							<view>晚餐</view>
						</view>
						<view class="" @click="add('2')" style="font-size: 30px;">
							+
						</view>

					</view>


					<view class="">
						<view class="gui-list-items" v-for="item in type2">
							<view class="gui-list-image gui-relative">
								<image class="gui-list-image" :src="item.cImg"></image>

							</view>
							<view class="gui-list-body gui-border-b">
								<view class="gui-list-title">

									<text class="gui-list-title-text gui-primary-color">{{item.cName}}</text>

								</view>

								<text class="gui-list-body-desc gui-color-gray"><text
										class="gui-color-red">{{item.cNum}}</text>
									克</text>
							</view>
							<view class="gui-flex gui-align-items-center" @tap="open1(item)">
								<view class=" gui-color-gray">
									{{item.cValue}}
								</view>
								<view class="" style="margin-left: 10upx;">


									<text class="gui-footer-icon-buttons-icon gui-block-text mint">&#xe691;</text>
								</view>
							</view>


						</view>
					</view>
				</view>

				<view class=" card gui-bg-white gui-margin-top">
					<view class="gui-flex gui-space-between gui-border-b">


						<view class="gui-text-center gui-flex">
							<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer">&#xe641;</text>
							<view>加餐</view>
						</view>
						<view class="" @click="add('3')" style="font-size: 30px;">
							+
						</view>

					</view>


					<view class="">
						<view class="gui-list-items" v-for="item in type3">
							<view class="gui-list-image gui-relative">
								<image class="gui-list-image" :src="item.cImg"></image>

							</view>
							<view class="gui-list-body gui-border-b">
								<view class="gui-list-title">

									<text class="gui-list-title-text gui-primary-color">{{item.cName}}</text>

								</view>

								<text class="gui-list-body-desc gui-color-gray"><text
										class="gui-color-red">{{item.cNum}}</text>
									克</text>
							</view>
							<view class="gui-flex gui-align-items-center" @tap="open1(item)">
								<view class=" gui-color-gray">
									{{item.cValue}}
								</view>
								<view class="" style="margin-left: 10upx;">


									<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer">&#xe678;</text>
								</view>
							</view>


						</view>
					</view>
				</view>

				<view class=" card gui-bg-white gui-margin-top">
					<view class="gui-flex gui-space-between gui-border-b">


						<view class="gui-text-center gui-flex">
							<text class="gui-footer-icon-buttons-icon gui-block-text mint">&#xe612;</text>
							<view>运动</view>
						</view>
						<view class="" @click="add('4')" style="font-size: 30px;">
							+
						</view>

					</view>


					<view class="">
						<view class="gui-list-items" v-for="item in type4">
							<view class="gui-list-image gui-relative">
								<image class="gui-list-image" :src="item.cImg"></image>

							</view>
							<view class="gui-list-body gui-border-b">
								<view class="gui-list-title">

									<text class="gui-list-title-text gui-primary-color">{{item.cName}}</text>

								</view>

								<text class="gui-list-body-desc gui-color-gray"><text
										class="gui-color-red">{{item.cNum}}</text>
									分钟</text>
							</view>
							<view class="gui-flex gui-align-items-center" @tap="open1(item)">
								<view class=" gui-color-gray">
									{{item.cValue}}
								</view>
								<view class="" style="margin-left: 10upx;">

									<text class="gui-footer-icon-buttons-icon gui-block-text mint">&#xe60d;</text>
								</view>
							</view>


						</view>
					</view>
				</view>



				<view class="card-body gui-card-body card gui-box-shadow gui-border-b">

					<view style="color: #f9a69f;margin-bottom: 10rpx;display: flex; align-items: center;"
						@click="goYsInfo()">
						<view><text class="gui-grids-icon mint" style="font-size: 20px;"> &#xe616;
							</text></view>
						<view style="margin-left: 10px;margin-top: -10rpx;">
							营养摄入分析</view>
					</view>

					<view class="gui-grids  gui-wrap gui-margin-top gui-space-between">


						<view class="list-txt">

							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>蛋白质</view>
								<span style="width:200rpx">{{food.protein}}克</span>

							</view>

							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>脂肪</view>
								<span style="width:200rpx">{{food.fat}}克</span>

							</view>

							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>碳水化合物</view>
								<span style="width:200rpx">{{food.carbohydrate}}克</span>

							</view>

							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>膳食纤维</view>
								<span style="width:200rpx">{{food.fiberDietary}}克</span>

							</view>

							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>糖</view>
								<span style="width:200rpx">{{food.sugar}}克</span>

							</view>

							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>钠</view>
								<span style="width:200rpx">{{food.natrium}}毫克</span>

							</view>

							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>维生素A</view>
								<span style="width:200rpx">{{food.vitaminA}}毫克</span>

							</view>

							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>维生素B1</view>
								<span style="width:200rpx">{{food.thiamine}}毫克</span>

							</view>

							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>维生素B2</view>
								<span style="width:200rpx">{{food.lactoflavin}}毫克</span>

							</view>

							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>维生素C</view>
								<span style="width:200rpx">{{food.vitaminC}}毫克</span>

							</view>


							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>维生素E</view>
								<span style="width:200rpx">{{food.vitaminE}}毫克</span>

							</view>

							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>烟酸</view>
								<span style="width:200rpx">{{food.niacin}}毫克</span>

							</view>

							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>钙</view>
								<span style="width:200rpx">{{food.calcium}}毫克</span>

							</view>

							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>钾</view>
								<span style="width:200rpx">{{food.kalium}}毫克</span>

							</view>

							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>铁</view>
								<span style="width:200rpx">{{food.iron}}毫克</span>

							</view>

							<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
								<view>锌</view>
								<span style="width:200rpx">{{food.zinc}}毫克</span>

							</view>
						</view>


					</view>



				</view>
				<view class="card-body gui-card-body gui-box-shadow gui-border-b card">
					<view class="charts-box">
						<qiun-data-charts type="line" :opts="opts" :chartData="chartData" />
					</view>
					<view class="charts-box">
						<qiun-data-charts type="line" :opts="opts2" :chartData="chartData2" />
					</view>
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
						<input type="text" class="gui-form-input" v-model="weight" name="name1" placeholder="kg" />
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


		<gui-calendar ref="guicalendar1" @confirm="changeDate1" :isTime="false"></gui-calendar>


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
				food: {},
				date1: null,
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
				categories: [{
						"name": "主食类",
						'imageUrl': "http://up.boohee.cn/house/u/food_library/category/2_v1.png",
						"id": 1
					},
					{
						"name": "主食类",
						'imageUrl': "http://up.boohee.cn/house/u/food_library/category/2_v1.png",
						"id": 2
					},
					{
						"name": "主食类",
						'imageUrl': "http://up.boohee.cn/house/u/food_library/category/2_v1.png",
						"id": 3
					},
					{
						"name": "主食类",
						'imageUrl': "http://up.boohee.cn/house/u/food_library/category/2_v1.png",
						"id": 4
					},
					{
						"name": "主食类",
						'imageUrl': "http://up.boohee.cn/house/u/food_library/category/2_v1.png",
						"id": 5
					},
					{
						"name": "主食类",
						'imageUrl': "http://up.boohee.cn/house/u/food_library/category/2_v1.png",
						"id": 6
					},
					{
						"name": "主食类",
						'imageUrl': "http://up.boohee.cn/house/u/food_library/category/2_v1.png",
						"id": 7
					},
					{
						"name": "主食类",
						'imageUrl': "http://up.boohee.cn/house/u/food_library/category/2_v1.png",
						"id": 8
					},
					{
						"name": "主食类",
						'imageUrl': "http://up.boohee.cn/house/u/food_library/category/2_v1.png",
						"id": 9
					},
					{
						"name": "主食类",
						'imageUrl': "http://up.boohee.cn/house/u/food_library/category/2_v1.png",
						"id": 10
					},
					{
						"name": "主食类",
						'imageUrl': "http://up.boohee.cn/house/u/food_library/category/2_v1.png",
						"id": 11
					},
					{
						"name": "主食类",
						'imageUrl': "http://up.boohee.cn/house/u/food_library/category/2_v1.png",
						"id": 12
					},
				],
				del: {

				},
				recommends: [],
				recentDates: [],
				intake: [],
				intake2:[],
				chartData: {},
				chartData2: {},
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
							max: 2000
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
				},
				opts2: {
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
							max: 2000
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
				},

			}
		},
		onShow() {
			this.getUserCalory()
		},
		onLoad() {
			var date = new Date();

			this.date1 = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
			uni.hideTabBar()
			this.getUserCalory()

			this.getCategorList()
			this.sevenDateInfo()
		},
		methods: {
			openCalendar1: function() {
				this.$refs.guicalendar1.open();
			},
			changeDate1: function(e) {
				this.date1 = e;
				this.getUserCalory()
			},
			goYsInfo() {
				uni.navigateTo({
					url: ""
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

				var params = {
					uid: graceJS.getStorage("id"),
					createTime: this.date1
				}

				uni.gRequest.post("bohe/calories", {
					data: params
				}, (res) => {
					console.log(res.data)
					if (res.data.code == 200) {
						this.food = res.data.food
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
			formatDate(date) {
				console.log(date)
				const year = date.getFullYear();
				let month = '' + (date.getMonth() + 1);
				let day = '' + date.getDate();

				if (month.length < 2) month = '0' + month;
				if (day.length < 2) day = '0' + day;

				return [year, month, day].join('-');
			},

			sevenDateInfo() {
				this.recentDates = []
				this.intake = []
				this.intake2 = []
				const today = new Date();
				for (let i = 6; i >= 0; i--) {
					const date1 = new Date(today);
					date1.setDate(today.getDate() - i);
					this.getInfo(date1)
					// intake.push();

				}
				console.log(this.recentDates)
				console.log(this.intake)
				console.log(this.intake2)
				var that = this
				setTimeout(function() {
					var ast = {
						categories: that.recentDates,
						xAxis: {
							fontSize: 10
						},
						series: [{
								// name: "血糖记录",
								name: "饮食摄入记录",
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
								data: that.intake
							},

						]
					}
					
					var ast2 = {
						categories: that.recentDates,
						xAxis: {
							fontSize: 10
						},
						series: [{
								// name: "血糖记录",
								name: "运动记录",
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
								data: that.intake2
							},
					
						]
					}

					that.chartData2 = JSON.parse(JSON.stringify(ast2))
					that.chartData = JSON.parse(JSON.stringify(ast))
					
				}, 1000);



			},

			getInfo(date) {
				var that = this
				var params = {
					uid: graceJS.getStorage("id"),
					createTime: date
				}

				uni.gRequest.post("bohe/calories", {
					data: params
				}, (res) => {
					console.log(res.data)
					if (res.data.code == 200) {
						var food = res.data.food
						var calorys = res.data.data
						var caloryNum = 0
						var ydNum = 0
						var foodNum = 0
						var type0 = []
						var type1 = []
						var type2 = []
						var type3 = []
						var type4 = []

						console.log(calorys)
						for (let i = 0; i < calorys.length;) {

							console.log(calorys[i])
							if (calorys[i].cType == 4) {
								type4.push(calorys[i])
								ydNum = ydNum + calorys[i].cValue
								caloryNum = caloryNum - calorys[i].cValue
							} else {
								switch (calorys[i].cType) {
									case 0:
										type0.push(calorys[i])
										break;
									case 1:
										type1.push(calorys[i])
										break;
									case 2:
										type2.push(calorys[i])
										break;
									case 3:
										type3.push(calorys[i])
										break;
									default:
										break;
								}
								foodNum = foodNum + calorys[i].cValue
								caloryNum = caloryNum + calorys[i].cValue
							}
							i++;
						}

						caloryNum = parseInt(caloryNum) //可以吃多少
						ydNum = parseInt(ydNum) //饮食摄入
						foodNum = parseInt(foodNum) //运动消耗
						console.log(caloryNum, ydNum, foodNum)
						// let map = {
						// 	caloryNum : parseInt(caloryNum) //可以吃多少
						// 	ydNum : parseInt(ydNum) //饮食摄入
						// 	foodNum : parseInt(foodNum) //运动消耗
						// 	date:date
						// }
						let dates = that.recentDates
						dates.push(this.formatDate(date));
						that.recentDates = dates;
						let numbers = that.intake;
						numbers.push(foodNum)
						let numbers2 = that.intake2;
						numbers2.push(ydNum)
						that.intake = numbers;
						that.intake2 = numbers2
					}

				})

			},




			getWeight() {
				uni.gRequest.post("bohe/getDayWeight", {
					data: {
						userId: graceJS.getStorage("id")
					}
				}, (res) => {
					console.log(res.data.data)
					try {
						if (res.data.data.length > 0) {
							this.kg = res.data.data[0].weight
						} else {
							this.kg = 0
						}
					} catch (e) {
						this.kg = 0
					}

				})
			},
			//获取分类数据
			getCategorList() {
				uni.gRequest.post("bohe/categorisList", {}, (res) => {
					console.log(res.data)
					if (res.data.code == 200) {
						this.categories = res.data.data
					}

				})
			},
			add(e) {
				// alert(e)
				var date = new Date();

				if (this.date1 === (date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate())) {
					if (e != 4) {

						graceJS.navigate("../../c-list/c-list?type=" + e)
					} else {
						graceJS.navigate("../../c-move-list/c-move-list")
					}

				} else {
					uni.showModal({
						content: "仅可添加今日饮食"
					})
				}
				console.log(e)

			},
			//分类列表/*  */
			getCategorise(e) {
				// alert(e)

				graceJS.navigate('../../list/list?id=' + e);
			},
			//打开删除弹窗
			open1: function(e) {
				
				this.del = e
				
				
				
				var date = new Date();
				
				if (this.date1 === (date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate())) {
					this.$refs.guimodal1.open();
				
				} else {
					uni.showModal({
						content: "不可删除之前饮食记录"
					})
				}
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

				uni.gRequest.post("bohe/setUserWeight", {
					data: {
						userId: graceJS.getStorage("id"),
						weight: this.kg
					}
				}, (res) => {
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
		}
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

	.list-txt view {
		border-bottom: 1px solid ghostwhite;
	}

	.list-txt view view {
		border-bottom: 0px;
		width: 35%;
		text-align: center;
	}

	.list-txt view span {
		width: 20%;
		text-align: center;
	}

	.list-txt text {
		width: 33%;
		text-align: right;
	}
</style>