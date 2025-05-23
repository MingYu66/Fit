<template>

	<view>
		<view style="margin-top: 20px; text-align: center;">
			<view class="gui-card-view gui-margin-top gui-box-shadow" v-for="item in list" @click="goInfo(item)">
				<view class="gui-card-body gui-border-b gui-flex gui-rows gui-nowrap">

					<view class="gui-card-desc">
					
						<text class="gui-card-name">{{item.title}}</text>
						
						<text class="gui-card-text gui-block-text" style="margin-top:10rpx;">{{item.remark}}</text>
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
				list: []
			}
		},
		onLoad() {
			var type = graceJS.getStorage("type")
			console.log(type)
			uni.gRequest.post("bohe/bhMenuList", {
				data:{remark:type}
			}, (res) => {
				console.log(res.data.data)
				this.list = res.data.data
			})
		},
		methods: {
			goInfo(e){
				uni.navigateTo({
					url:"../menu-info/menu-info?id=" + e.id
				})
			}
		}
	}
</script>

<style>
	/* 卡片视图 */
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

	/* #endif */
</style>