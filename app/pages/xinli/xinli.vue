<template>
	<view class="gui-margin">
		<view class="gui-card-list gui-flex gui-rows gui-wrap gui-space-between gui-margin-top">
			<view class="gui-card-item" hover-class="gui-tap" v-for="(item,index) in data" v-key="index" @click="go(index)">
				<view class="gui-card-img">
					<gui-image :width="330" :height="191" :src="'http://192.168.3.4/dev-api'+item.img"></gui-image>
				</view>
				<text class="gui-card-title gui-block-text gui-text gui-primary-color">{{item.title}}</text>
				<text class="gui-block-text gui-text-small gui-color-gray">{{item.createTime}}</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				data: []
			}
		},
		onLoad() {
			uni.gRequest.post("bohe/xinliList", {

			}, (res) => {
				console.log(res.data)
				this.data = res.data.data
			})
		},
		methods: {
			go(e){
				uni.navigateTo({
					url:"info?id=" + e
				})
			}
		}
	}
</script>

<style>
/* 卡片宽度 = 外层宽度 （690 / 一行数量 2） - 间距 */
/* 实际使用过程中请跟据项目要求进行宽度修改实现一行多列 */
.gui-card-item{width:330rpx; margin-bottom:30rpx;}
/* 图片宽度、高度修饰 */
.gui-card-img{width:330rpx; height:191rpx; overflow:hidden; position:relative;}
/* 标题顶部间距 */
.gui-card-title{margin-top:3px;}
/* 描述顶部间距 */
.gui-card-desc{margin-top:3px;}
/* 定位标签样式 */
.gui-card-tip{width:68rpx; height:40rpx; line-height:40rpx; text-align:center;}
/* 定位于图片上方的标题修饰 */
.gui-card-mask-title{line-height:40rpx; padding:10rpx; width:330rpx;}
</style>