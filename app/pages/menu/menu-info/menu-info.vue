<template>
	<view style="padding: 10px;">
		<view class=" card gui-bg-white gui-margin-top" style="text-align: center;">
			
			{{info.title}}
		
		</view>
		<view class=" card gui-bg-white gui-margin-top" v-if="type0.length > 0">
			<view class="gui-flex gui-space-between gui-border-b">


				<view class="gui-text-center gui-flex">
					<text class="gui-footer-icon-buttons-icon gui-block-text mint">&#xf221;</text>
					<view>早餐</view>
				</view>


			</view>


			<view class="">

				<view class="gui-list-items" v-for="item in type0" @click="gofood(item.food)">
					<view class="gui-list-image gui-relative">
						<image class="gui-list-image" :src="item.food.thumbImageUrl"></image>

					</view>
					<view class="gui-list-body gui-border-b">
						<view class="gui-list-title">

							<text class="gui-list-title-text gui-primary-color">{{item.food.name}}</text>

						</view>
					</view>



				</view>
			</view>
		</view>

		<view class=" card gui-bg-white gui-margin-top" v-if="type1.length > 0">
			<view class="gui-flex gui-space-between gui-border-b">


				<view class="gui-text-center gui-flex">
					<text class="gui-footer-icon-buttons-icon gui-block-text mint">&#xe680;</text>
					<view>午餐</view>
				</view>


			</view>


			<view class="">
				<view class="gui-list-items" v-for="item in type1"  @click="gofood(item.food)">
					<view class="gui-list-image gui-relative">
						<image class="gui-list-image" :src="item.food.thumbImageUrl"></image>

					</view>
					<view class="gui-list-body gui-border-b">
						<view class="gui-list-title">

							<text class="gui-list-title-text gui-primary-color">{{item.food.name}}</text>

						</view>


					</view>



				</view>
			</view>
		</view>

		<view class=" card gui-bg-white gui-margin-top" v-if="type2.length > 0">
			<view class="gui-flex gui-space-between gui-border-b">


				<view class="gui-text-center gui-flex">
					<text class="gui-footer-icon-buttons-icon gui-block-text mint">&#xe6fa;</text>
					<view>晚餐</view>
				</view>


			</view>


			<view class="">
				<view class="gui-list-items" v-for="item in type2"  @click="gofood(item.food)">
					<view class="gui-list-image gui-relative">
						<image class="gui-list-image" :src="item.food.thumbImageUrl"></image>

					</view>
					<view class="gui-list-body gui-border-b">
						<view class="gui-list-title">

							<text class="gui-list-title-text gui-primary-color">{{item.food.name}}</text>

						</view>


					</view>



				</view>
			</view>
		</view>
	
	</view>
</template>

<script>
	export default {
		data() {
			return {
				info: {},
				type0: [{
					food: {
						name: "",
						thumbImageUrl: ""
					}
				}],
				type1: [{
					food: {
						name: "",
						thumbImageUrl: ""
					}
				}],
				type2: [{
					food: {
						name: "",
						thumbImageUrl: ""
					}
				}],

			}
		},
		onLoad(e) {
			this.getinfo(e.id)
		},
		methods: {
			getinfo(id) {
				var that = this
				uni.gRequest.post("bohe/bhMenuInfo", {
					data: {
						id: id
					}
				}, (res) => {
					console.log(res)
					this.info = res.data.data
					console.log(this.info)
					var type0 = []
					var type1 = []
					var type2 = []
					var type3 = []
					var type4 = []

					for (let i = 0; i < this.info.bhMenuInfoList.length;) {


						switch (this.info.bhMenuInfoList[i].type) {
							case 0:
								type0.push(this.info.bhMenuInfoList[i])
								break;
							case 1:
								type1.push(this.info.bhMenuInfoList[i])
								break;
							case 2:
								type2.push(this.info.bhMenuInfoList[i])
								break;
							case 3:
								type3.push(this.info.bhMenuInfoList[i])
								break;
							default:
								break;
						}


						i++;
					}
					this.type0 = type0
					this.type1 = type1
					this.type2 = type2


					console.log(this.type0)
				})
			},
			gofood(item){
				uni.navigateTo({
					url:"/pages/foodInfo/foodInfo?item="+ encodeURIComponent(JSON.stringify(item))
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