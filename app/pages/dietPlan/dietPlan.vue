<template>
	<view style="padding: 10px;">
		<view class=" card gui-bg-white gui-margin-top" style="text-align: center;" @click="getPlan()">

			不喜欢？点击重新推荐

		</view>
		<view class=" card gui-bg-white gui-margin-top" v-if="MORNING.items.length > 0">
			<view class="gui-flex gui-space-between gui-border-b gui-align-items-center">


				<view class="gui-text-center gui-flex gui-align-items-center">
					<text class="gui-footer-icon-buttons-icon gui-block-text mint">&#xf221;</text>
					<view>早餐</view>
				</view>
				<view class="gui-list-body gui-border-b">
					<view class="gui-list-title" style="font-size: 10rpx;">
						<text class=" gui-primary-color">热量{{toFixed(MORNING.totalCalories)}}</text>
						<text class=" gui-primary-color">蛋白质{{toFixed(MORNING.totalProtein)}}</text>
						<text class=" gui-primary-color">脂肪{{toFixed(MORNING.totalFat)}}</text>
						<text class=" gui-primary-color">碳水{{toFixed(MORNING.totalCarbohydrate)}}</text>
				
					</view>
				</view>

			</view>


			<view class="">

				<view class="gui-list-items gui-flex gui-justify-content-start" v-for="item in MORNING.items" @click="gofood(item.food)">
					<view class=" gui-flex gui-space-between gui-align-items-center" style="width: 80%;">
						<view class="gui-list-image gui-relative">
							<image class="gui-list-image" :src="item.food.thumbImageUrl"></image>
						
						</view>
						<view class="gui-list-body gui-border-b">
							<view class="gui-list-title">
						
								<text class="gui-list-title-text gui-primary-color">{{item.food.name}}</text>
						
							</view>
						</view>
					</view>
					
					<view class="gui-list-body gui-border-b">
						<view class="gui-list-title">
					
							<text class="gui-list-title-text gui-primary-color">{{toFixed(item.grams)}}g</text>
					
						</view>
					</view>

				</view>
				
			</view>
		</view>

		<view class=" card gui-bg-white gui-margin-top" v-if=" NOON.items.length > 0">
			<view class="gui-flex gui-space-between gui-border-b">


				<view class="gui-text-center gui-flex gui-align-items-center">
					<text class="gui-footer-icon-buttons-icon gui-block-text mint">&#xe680;</text>
					<view>午餐</view>
				</view>
<view class="gui-list-body gui-border-b">
					<view class="gui-list-title" style="font-size: 10rpx;">
						<text class=" gui-primary-color">热量{{toFixed(NOON.totalCalories)}}</text>
						<text class=" gui-primary-color">蛋白质{{toFixed(NOON.totalProtein)}}</text>
						<text class=" gui-primary-color">脂肪{{toFixed(NOON.totalFat)}}</text>
						<text class=" gui-primary-color">碳水{{toFixed(NOON.totalCarbohydrate)}}</text>
				
					</view>
				</view>

			</view>


			<view class="">
				<view class="gui-list-items" v-for="item in  NOON.items" @click="gofood(item.food)">
					<view class=" gui-flex gui-space-between gui-align-items-center" style="width: 80%;">
						<view class="gui-list-image gui-relative">
							<image class="gui-list-image" :src="item.food.thumbImageUrl"></image>
						
						</view>
						<view class="gui-list-body gui-border-b">
							<view class="gui-list-title">
						
								<text class="gui-list-title-text gui-primary-color">{{item.food.name}}</text>
						
							</view>
						</view>
					</view>
					
					<view class="gui-list-body gui-border-b">
						<view class="gui-list-title">
					
							<text class="gui-list-title-text gui-primary-color">{{toFixed(item.grams)}}g</text>
					
						</view>
					</view>



				</view>
			</view>
		</view>

		<view class=" card gui-bg-white gui-margin-top" v-if=" EVENING.items.length > 0">
			<view class="gui-flex gui-space-between gui-border-b">


				<view class="gui-text-center gui-flex gui-align-items-center">
					<text class="gui-footer-icon-buttons-icon gui-block-text mint">&#xe6fa;</text>
					<view>晚餐</view>
				</view>
<view class="gui-list-body gui-border-b">
					<view class="gui-list-title" style="font-size: 10rpx;">
						<text class=" gui-primary-color">热量{{toFixed(EVENING.totalCalories)}}</text>
						<text class=" gui-primary-color">蛋白质{{toFixed(EVENING.totalProtein)}}</text>
						<text class=" gui-primary-color">脂肪{{toFixed(EVENING.totalFat)}}</text>
						<text class=" gui-primary-color">碳水{{toFixed(EVENING.totalCarbohydrate)}}</text>
				
					</view>
				</view>

			</view>


			<view class="">
				<view class="gui-list-items" v-for="item in  EVENING.items" @click="gofood(item.food)">
					<view class=" gui-flex gui-space-between gui-align-items-center" style="width: 80%;">
						<view class="gui-list-image gui-relative">
							<image class="gui-list-image" :src="item.food.thumbImageUrl"></image>
						
						</view>
						<view class="gui-list-body gui-border-b">
							<view class="gui-list-title">
						
								<text class="gui-list-title-text gui-primary-color">{{item.food.name}}</text>
						
							</view>
						</view>
					</view>
					
					<view class="gui-list-body gui-border-b">
						<view class="gui-list-title">
					
							<text class="gui-list-title-text gui-primary-color">{{toFixed(item.grams)}}g</text>
					
						</view>
					</view>


				</view>
			</view>
		</view>

	</view>
</template>

<script>
	var graceJS = require('@/GraceUI5/js/grace.js');
	export default {
		data() {
			return {
				useCalory: 0,
				EVENING: {},
				MORNING: {},
				NOON: {}

			}
		},
		onLoad() {
			this.getPlan()
		},
		methods: {
			toFixed(val){
						var str = val.toFixed(0)
						return str;
					},
				
			getPlan() {
				uni.showLoading({
				                    title: '加载中'
				                });

				this.useCalory = graceJS.getStorage("calory")
				var date = new Date();

				uni.gRequest.post("bohe/getDietRecommender", {
					data: {
						id: graceJS.getStorage("id")
					}
				}, (res) => {
					uni.hideLoading();

					this.EVENING = res.data.data.EVENING
					this.MORNING = res.data.data.MORNING

					this.NOON = res.data.data.NOON

					console.log(this.EVENING, this.MORNING, this.NOON)
				})
			},
			gofood(item) {
				uni.navigateTo({
					url: "/pages/foodInfo/foodInfo?item=" + encodeURIComponent(JSON.stringify(item))
				})
			}
		}
	}
</script>

<style>
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
</style>