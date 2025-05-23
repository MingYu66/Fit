<template>
	<view slot="gBody" class="gui-margin" style="padding-bottom:50rpx; ">
		<view class="my-list">

			<navigator  class="gui-list-items" v-for="item in list"
				:url="'/pages/foodInfo/foodInfo?item='+ encodeURIComponent(JSON.stringify(item))">
				<view class="gui-list-image gui-relative">
					<image class="gui-list-image" :src="item.thumbImageUrl"></image>

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
			</navigator>



		</view>

		

	</view>
</template>

<script>
	const list = require('@/data/food.json')
	var graceJS = require('@/GraceUI5/js/grace.js');
	export default {
		data() {
			return {
				list: []
			}
		},
		onLoad(data) {
			var pamars = {
				'uid': parseInt(graceJS.getStorage("id"))
			}
			console.log(pamars)
			uni.gRequest.post("bohe/loveFoods", {
				data: pamars
			}, (res) => {
				console.log(res.data)
				if (res.data.code == 200) {
					this.list = res.data.data
					
					
				}
				
				console.log(this.list)

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
			
		}
	}
</script>

<style>

</style>