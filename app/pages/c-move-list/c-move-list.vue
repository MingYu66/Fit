<template>
	<view slot="gBody" class="gui-margin" style="padding-bottom:50rpx; ">
		<view class="my-list">

			<view  @tap="open1(item)" class="gui-list-items" v-for="item in list">
				<view class="gui-list-image gui-relative">
					<image class="gui-list-image" :src="item.img"></image>

				</view>
				<view class="gui-list-body gui-border-b">
					<view class="gui-list-title">

						<text class="gui-list-title-text gui-primary-color">{{item.name}}</text>

					</view>

					<text class="gui-list-body-desc gui-color-gray"><text class="gui-color-red">{{item.calory}}</text>
						千卡/{{item.weight}}克</text>
				</view>
				<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-green" v-if="item.healthLight == 1"
					style="border-radius: 100%;width: 20upx;height:20upx;"></text>
				<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-yellow" v-if="item.healthLight == 2"
					style="border-radius: 100%;width: 20upx;height:20upx;"></text>
				<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-red" v-if="item.healthLight == 3"
					style="border-radius: 100%;width: 20upx;height:20upx;"></text>
				<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-black" v-if="item.healthLight == 4"
					style="border-radius: 100%;width: 20upx;height:20upx;"></text>
			</view>



		</view>

		<gui-modal ref="guimodal1"
			:title="food.createTime +' '+ food.name+' 运动'">
			<view slot="content" class="gui-padding gui-bg-gray">
				<view class="gui-flex gui-rows gui-nowrap gui-cate-product-list gui-align-items-center">

					<view class="gui-list-image gui-relative">
						<image class="gui-list-image" :src="food.img"></image>

					</view>
					<view class="gui-list-body gui-border-b">
						<view class="gui-list-title">

							<text class="gui-list-title-text gui-primary-color">{{food.name}}</text>

						</view>

						<text class="gui-list-body-desc gui-color-gray"><text
								class="gui-color-red">{{food.calory}}</text>
							千卡/{{food.weight}}分钟</text>
					</view>

				</view>

				<view class="gui-flex gui-align-items-center gui-justify-content-start">
					<view class="gui-color-gray gui-flex gui-text-small" style="width: 30%;">
						{{food.calory * form.value / food.weight}} 千卡</view>
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
	const list = require('@/data/food.json')
	var graceJS = require('@/GraceUI5/js/grace.js');
	export default {
		data() {
			return {
				list: [],
				form:{
					value:60
				},
				food:{
					
				}
				
			}
		},
		onLoad(data) {
	
			uni.gRequest.post("bohe/movesList", {
				
			}, (res) => {
				console.log(res.data)
				if (res.data.code == 200) {
					this.list = res.data.data
				}

			})


			// let l = JSON.stringify(list)
			// l = JSON.parse(l)
			// console.log(l)
			// graceJS.get('/static/food.json',
			// 	(res) => {
			// 		console.log(res);
			// 	}
			// );
			// this.list = l.foods



		},
		methods: {
			open1: function(e) {
				var date = new Date();


				this.food = e
				this.food.createTime = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()

				this.$refs.guimodal1.open();
			},
			close1: function() {
				this.$refs.guimodal1.close();
			},
			confirm1: function() {
			
				console.log(this.food)
				console.log(this.food.calory * this.form.value / this.food.weight)
				var params = {
					cType: 4,
					cValue: this.food.calory * this.form.value / this.food.weight,
					cName: this.food.name,
					cId: this.food.id,
					cImg: this.food.img,
					uid: graceJS.getStorage("id"),
					cNum: this.form.value,
					cWeight: this.food.weight,
					createTime: this.food.createTime
				}
				console.log(params)
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

<style>
.modal-btns{line-height:88rpx; font-size:26rpx; text-align:center; width:200rpx;}
</style>