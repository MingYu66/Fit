<template>
	<view>
		<view class=" card-body gui-card-body card  gui-border-b">
			
			<view class="gui-grids gui-flex gui-rows gui-wrap gui-space-between">
				
		
				<view v-for="(item, idx) in categories" style="width: 25%;"
					class="gui-grids-items gui-flex gui-columns gui-align-items-center"
					@click="getCategorise(item.id)">
					<image class="gui-grids-icon-img" mode="aspectFill" :src="item.imageUrl"
						style=" width:50px;height:50px;border: none;"></image>
					<text class="gui-grids-text gui-icons gui-primary-color">{{item.name}}</text>
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
				categories:[]
			}
		},
		onLoad() {
			this.getCategorList()
		},
		methods: {
			//获取分类数据
			getCategorList() {
				uni.gRequest.post("bohe/categorisList", {}, (res) => {
					console.log(res.data)
					if (res.data.code == 200) {
						this.categories = res.data.data
					}
			
				})
			},
			//分类列表/*  */
			getCategorise(e) {
				// alert(e)
			
				graceJS.navigate('../list?id=' + e);
			},
		}
	}
</script>

<style>
	
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
	
</style>
