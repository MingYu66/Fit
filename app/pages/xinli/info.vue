<template>
	<view>
		
		<!-- 视频播放区域 -->
		<view>
			<video id="myVideo" class="gui-course-video" :src="'http://192.168.3.4/dev-api'+courseList[playIndex].mp4"
				:poster="courseList[playIndex].i" controls></video>
		</view>
		<!-- 基础信息 -->
		<view class="gui-course-info gui-flex gui-rows gui-nowrap gui-space-between gui-align-items-center">
			<view class="gui-course-info-body">
				<view class="gui-course-title">{{courseList[playIndex].title}}</view>
				<view class="gui-flex gui-rows">
					<text class="gui-course-info-text gui-block-text">发布时间 : {{courseList[playIndex].createTime}}</text>
				</view>
			</view>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				courseList: [{
					mp4: "http://192.168.3.4/dev-api/profile/upload/2024/05/08/mda-qaf5uvbh9v820hc6_20240508010826A002.mp4"
				}, ],
				playIndex: 0,
			}
		},
		onLoad(id) {
			this.playIndex = id.id
			console.log(this.playIndex)
			uni.gRequest.post("bohe/xinliList", {

			}, (res) => {

				this.courseList = res.data.data
				// this.courseList[0].mp4 = "http://192.168.3.4/dev-api" + this.courseList[0].mp4
				console.log(this.courseList[0].mp4)
				console.log(this.courseList[this.playIndex])
			})
		},
		methods: {

		}
	}
</script>

<style>
	.gui-course-video {
		width: 750rpx;
	}

	.gui-course-info {
		background-color: #FFFFFF;
		padding: 20rpx 25rpx;
	}

	.gui-course-info-body {
		width: 200rpx;
		flex: 1;
	}

	.gui-course-info-share {
		width: 100rpx;
	}

	.gui-course-title {
		font-size: 32rpx;
		line-height: 50rpx;
		color: #333333;
	}

	.gui-course-info-text {
		font-size: 22rpx;
		color: #999999;
		line-height: 38rpx;
		margin-right: 30rpx;
		margin-top: 8rpx;
	}
</style>