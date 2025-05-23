<template>
	<view class="">
		<gui-page style="background-color: #f6f7fa;">
			<view slot="gBody" class="gui-margin">
				<view class="card-body gui-card-body gui-box-shadow gui-border-b">

					<view class="card-body1">
						<view class="card-body11 gui-flex gui-justify-content-end gui-align-items-center "
							style="height: 20upx;">
							<view class="r_txt gui-flex gui-justify-content-end gui-align-items-center"
								style="background-color: #f0fffb;">


								<view class="r_txt gui-flex gui-justify-content-end gui-align-items-center"
									style="background-color: #f0fffb;" v-if="food.healthLight == 1">


									<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-green"
										style="border-radius: 100%;width: 20upx;height:20upx;">

									</text>
									<text class="gui-text-small gui-color-green">绿灯食物</text>
									<text class="gui-text-small gui-color-gray-light">推荐食用</text>
								</view>


								<view class="r_txt gui-flex gui-justify-content-end gui-align-items-center"
									style="background-color: #fcff14;" v-if="food.healthLight == 2">


									<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-yellow"
										style="border-radius: 100%;width: 20upx;height:20upx;">

									</text>
									<text class="gui-text-small gui-color-green">黄灯食物</text>
									<text class="gui-text-small gui-color-green">可以食用</text>
								</view>


								<view class="r_txt gui-flex gui-justify-content-end gui-align-items-center"
									style="background-color: #fda697;color: #333;" v-if="food.healthLight == 3">


									<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-red"
										style="border-radius: 100%;width: 20upx;height:20upx;">

									</text>
									<text class="gui-text-small gui-color-black">红灯食物</text>
									<text class="gui-text-small gui-color-black">少量使用</text>
								</view>


								<view class="r_txt gui-flex gui-justify-content-end gui-align-items-center"
									style="background-color: #ffffff;" v-if="food.healthLight == 4">


									<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-black"
										style="border-radius: 100%;width: 20upx;height:20upx;">

									</text>
									<text class="gui-text-small gui-color-black">黑灯食物</text>
									<text class="gui-text-small gui-color-black">不推荐使用</text>
								</view>
							</view>


						</view>
						<view class="card-body12 gui-flex gui-align-items-center ">
							<view class=" gui-relative">
								<image class=" gui-list-image ucenter-face-image" style="width: 120upx;height: 120upx;"
									:src="food.thumbImageUrl">
								</image>

							</view>
							<view class="gui-margin">{{food.name}}</view>
						</view>

						<view
							class="gui-border-t gui-margin-top gui-flex gui-align-items-center gui-justify-content-center">
							<view class="gui-grids-items gui-flex gui-align-items-center gui-grids4"
								@click="love(food.id)">
								<text class="gui-grids-icon gui-icons " style="font-size:20px">&#xe634;</text>
								<text class="gui-grids-text "
									style="margin-left: -65%;">{{status == 1 ? '已': ''}}收藏</text>
							</view>
							<view class="gui-color-gray gui-text-center"> | </view>
							<view class="gui-grids-items gui-flex gui-align-items-center gui-grids4"
								@tap="open1(product)">
								<text class="gui-grids-icon gui-icons " style="font-size:20px">&#xe625;
								</text>
								<text class="gui-grids-text " style="margin-left: -70%;">记录</text>
							</view>
						</view>


					</view>

					<view class="">

					</view>



				</view>
				
				<view class="card-body gui-card-body gui-box-shadow gui-border-b">
					<view class="gui-flex gui-justify-content-center" style="justify-content:space-between">
						<text class="gui-h4 gui-bold">食材</text>
						<text class="gui-color-gray"></text>
					</view>
					<view style="white-space: pre-wrap;margin-top: 10rpx;" class="gui-color-gray">
						{{food.descInfo != null ? food.descInfo : food.name}}
					</view>
				</view>

				<view class="card-body gui-card-body gui-box-shadow gui-border-b">
					<view class="gui-flex gui-justify-content-center" style="justify-content:space-between">
						<text class="gui-h4 gui-bold">营养信息</text>
						<text class="gui-color-gray">每「100克」可食部</text>
					</view>
					<view
						class="gui-flex gui-border-t gui-h3 gui-justify-content-center  gui-margin-top  gui-align-items-center"
						style="height: 200upx;">
						{{food.calory}} 千卡 / {{food.calory * 4}} 千焦
					</view>
					<view class="nav gui-flex  gui-space-around">
						<l-circle :current.sync="modelVale" :percent="food.protein" strokeColor="#fda697" size="80px"
							:clockwise="false">
							<view class="t_c" style="font-size: 26upx;color: #333; text-align: center;">
								<view>
									蛋白质
								</view>
								<view class="gui-bold gui-h6">
									<!-- {{food.protein}} -->
									{{food.protein}}%
								</view>

							</view>
						</l-circle>

						<l-circle :current.sync="modelVale" :percent="food.fat" strokeColor="#fecb76" size="80px"
							:clockwise="false">
							<view class="t_c" style="font-size: 26upx;color: #333; text-align: center;">
								<view>
									脂肪
								</view>
								<view class="gui-bold gui-h6">
									{{food.fat}}%
								</view>

							</view>
						</l-circle>

						<l-circle :current.sync="modelVale" :percent="food.carbohydrate" strokeColor="#a3cdb1"
							size="80px" :clockwise="false">
							<view class="t_c" style="font-size: 26upx;color: #333; text-align: center;">
								<view>
									碳水
								</view>
								<view class="gui-bold gui-h6">
									{{food.carbohydrate}}%
								</view>

							</view>
						</l-circle>



					</view>

					<view style="width: 100%;height:40upx; text-align: center; color: #bdbdbd;" class="gui-text-small">
						三大营养功能对比

					</view>

					<view
						class="gui-margin-top gui-flex gui-align-items-center gui-space-around gui-bg-gray gui-color-gray">
						<text>项目</text>
						<text>每「100克」可食部</text>
						<!-- <text>NRV%</text> -->
					</view>
					<view class="list-txt">
						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>热量</view>
							<span>{{food.calory}}千卡</span>
							<!-- <!-- <text>0.1%</text> -->
						</view>
						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>蛋白质</view>
							<span>{{food.protein}}克</span>
							<!-- <text>0.1%</text> -->
						</view>

						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>脂肪</view>
							<span>{{food.fat}}克</span>
							<!-- <text>0.1%</text> -->
						</view>

						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>碳水化合物</view>
							<span>{{food.carbohydrate}}克</span>
							<!-- <text >0.1%</text> -->
						</view>

						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>膳食纤维</view>
							<span>{{food.fiberDietary}}克</span>
							<!-- <text>0.1%</text> -->
						</view>

						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>糖</view>
							<span>{{food.sugar}}克</span>
							<!-- <text>0.1%</text> -->
						</view>

						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>钠</view>
							<span>{{food.natrium}}毫克</span>
							<!-- <text>0.1%</text> -->
						</view>

						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>维生素A</view>
							<span>{{food.vitaminA}}毫克</span>
							<!-- <text>0.1%</text> -->
						</view>

						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>维生素B1</view>
							<span>{{food.thiamine}}毫克</span>
							<!-- <text>0.1%</text> -->
						</view>

						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>维生素B2</view>
							<span>{{food.lactoflavin}}毫克</span>
							<!-- <text>0.1%</text> -->
						</view>

						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>维生素C</view>
							<span>{{food.vitaminC}}毫克</span>
							<!-- <text>0.1%</text> -->
						</view>


						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>维生素E</view>
							<span>{{food.vitaminE}}毫克</span>
							<!-- <text>0.1%</text> -->
						</view>

						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>烟酸</view>
							<span>{{food.niacin}}毫克</span>
							<!-- <text>0.1%</text> -->
						</view>

						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>钙</view>
							<span>{{food.calcium}}毫克</span>
							<!-- <text>0.1%</text> -->
						</view>

						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>钾</view>
							<span>{{food.kalium}}毫克</span>
							<!-- <text>0.1%</text> -->
						</view>

						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>铁</view>
							<span>{{food.iron}}毫克</span>
							<!-- <text>0.1%</text> -->
						</view>

						<view class="gui-flex gui-margin-top gui-align-items-center gui-space-around">
							<view>锌</view>
							<span>{{food.zinc}}毫克</span>
							<!-- <text>0.1%</text> -->
						</view>
					</view>


				</view>


				<view style="width: 100%; height: 200upx;">

				</view>

			</view>
		</gui-page>
		<gui-modal ref="guimodal1"
			:title=" type == 0 ? food.createTime +' '+ food.name+' 早餐' : type == 1 ? food.createTime +' '+ food.name+' 午餐' : type== 2 ? food.createTime +' '+ food.name+' 晚餐' : type == 3 ? food.createTime +' '+ food.name+' 加餐' : food.createTime +' '+ food.name+' 运动'">
			<view slot="content" class="gui-padding gui-bg-gray" style="height: 200px;">
				<view class="gui-flex gui-rows gui-nowrap gui-cate-product-list gui-align-items-center">

					<view class="gui-list-image gui-relative">
						<image class="gui-list-image" :src="food.thumbImageUrl"></image>

					</view>

					<view class="gui-list-body gui-border-b">
						<view class="gui-list-title">

							<text class="gui-list-title-text gui-primary-color">{{food.name}}</text>

						</view>

						<text class="gui-list-body-desc gui-color-gray"><text
								class="gui-color-red">{{food.calory}}</text>
							千卡/{{food.weight}}克</text>
					</view>

					<view class="">
						
							 <uni-data-select
							      v-model="type"
							      :localdata="selectMenu2"
							    ></uni-data-select>
					</view>

				</view>

				<view class="gui-flex gui-align-items-center gui-justify-content-start">
					<view class="gui-color-gray gui-flex gui-text-small" style="width: 30%;">
						{{food.calory * form.value / food.weight}} 千卡
					</view>
					<view class="gui-text-center">
						<input type="number" name="value" v-model="form.value" id="">
					</view>
					<view class="gui-color-gray  gui-text-small" style="width: 30%;">
						估算重量
					</view>
				</view>
			</view>
			<!-- 利用 flex 布局 可以放置多个自定义按钮哦  -->
			<view slot="btns" class="gui-flex gui-rows gui-space-between">
				<view class="modal-btns gui-flex1" style="margin-right:80rpx;">
					<text class="modal-btns gui-color-gray" @tap="close1">取消</text>
				</view>
				<view class="modal-btns gui-flex1" style="margin-left:80rpx;">
					<text class="modal-btns gui-color-blue" @tap="confirm1">添加</text>
				</view>
			</view>
		</gui-modal>


	</view>
</template>
<script>
	var graceJS = require('@/GraceUI5/js/grace.js');
	export default {

		data() {
			return {
				type: 3,
				food: {

				},
				form: {
					value: 100
				},
				times: 0,
				modelVale: 0,
				percent: 500 / 1000 * 100,
				status: 0,
				// selectMenu2:['早餐','午餐','晚餐','加餐'],
				selectMenu2: [
					{
					value:0,
					text:"早餐"
				},
				{
					value:1,
					text:"午餐"
				},
				{
					value:2,
					text:"晚餐"
				},
				{
					value:3,
					text:"加餐"
				},
				],
			}
		},
		onLoad(option) {
			const item = JSON.parse(decodeURIComponent(option.item));
			this.food = item
			console.log(item)
			this.checkLoveStatus()
		},
		methods: {
			//收藏当前食物
			love() {
				var params = {
					uid: graceJS.getStorage("id"),
					fid: this.food.id
				}
				if (this.status == 0) {
					//收藏
					uni.gRequest.post("bohe/addloveFood", {
						data: params
					}, (res) => {
						console.log(res.data)
						if (res.data.code == 200) {
							this.status = 1

						}

					})
				} else {
					// 删除
					uni.gRequest.post("bohe/deleteBhLove", {
						data: params
					}, (res) => {
						console.log(res.data)
						if (res.data.code == 200) {
							this.status = 0

						}

					})
				}

			},
			//检测是否收藏当前食物
			checkLoveStatus() {
				var params = {
					uid: graceJS.getStorage("id"),
					fid: this.food.id
				}
				uni.gRequest.post("bohe/loveStatus", {
					data: params
				}, (res) => {
					console.log(res.data)
					if (res.data.code == 200) {
						this.status = res.data.data

					}

				})
			},
			add(e) {
				// alert(e)
				console.log(e)
			},
			getCategorise(e) {
				// alert(e)
				graceJS.navigate('../list/list?id=' + e);
			},
			open1: function() {
				var date = new Date();


				this.food.createTime = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()

				this.$refs.guimodal1.open();
			},
			close1: function() {
				this.$refs.guimodal1.close();
			},
			confirm1: function() {
				var params = {
					cType: this.type,
					cValue: this.food.calory * this.form.value / this.food.weight,
					cName: this.food.name,
					cId: this.food.id,
					cImg: this.food.thumbImageUrl,
					uid: graceJS.getStorage("id"),
					cNum: this.form.value,
					cWeight: this.food.weight,
					createTime: this.food.createTime
				}
				uni.gRequest.post("bohe/addCalory", {
					data: params
				}, (res) => {
					console.log(res.data)
					if (res.data.code == 200) {

					}

				})
				// 客户点击确认按钮后的逻辑请在此处实现
				this.$refs.guimodal1.close();
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

	.card-body11 {

		width: 100%;
		text-align: right;
	}

	.r_txt text {
		margin-left: 15upx;
	}

	.card-body12 {
		text-align: left;
	}

	/* 一行4格演示 */
	.gui-grids4 {
		width: 50%;
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

	.btn {
		display: flex;
		align-items: center;
		justify-content: space-around;


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

	.gui-grids-icon {
		color: rgba(69, 90, 100, 0.6);
	}

	.gui-grids-text {
		color: rgba(69, 90, 100, 0.8);
	}
</style>