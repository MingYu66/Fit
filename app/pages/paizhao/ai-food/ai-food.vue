<template>
	<view>
		<view class="card-body gui-card-body gui-box-shadow gui-border-b card">
			<view class="" style="display: flex; justify-content: space-around;align-items: center;">
				<view class="btnImg" >
					<text class="gui-color-white mint" style="font-size: 80rpx;">&#xe60e;</text>
					食物识别
				</view>
			</view>
		</view>
		
		<uni-file-picker 
			v-model="imageValue" 
			fileMediatype="image" 
			mode="grid" 
			@select="select" 
			@progress="progress" 
			@success="success" 
			:auto-upload="true"
			@fail="fail" 
		/>
		
		
			<!-- <view class="gui-table gui-border-l gui-border-t" style="margin-top:50rpx;">
						<view class="gui-theader gui-flex gui-rows gui-nowrap gui-bg-gray">
							<text class="gui-td gui-border-r gui-border-b gui-td-text gui-bold gui-text-center">配料名</text>
							<text class="gui-td gui-border-r gui-border-b gui-td-text gui-bold gui-text-center">简介</text>
							
						</view>
						<view class="gui-tbody gui-flex gui-rows gui-nowrap" 
						v-for="(item, index) in list" :key="index">
							<text class="gui-td gui-td-text gui-text-center gui-border-r gui-border-b">{{item.name}}</text>
							<text class="gui-td gui-td-text gui-text-center gui-border-r gui-border-b">{{item.desc}}</text>
							
						</view>
					</view> -->
		<view class="card-body gui-card-body gui-border-b card">
			
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
		
		
		
		
	</view>
</template>

<script>
import request from '../../../GraceUI5/js/request/request'
	export default {
		data() {
			return {
				list:[
					
				],
				imageValue:[]
			}
		},
		methods: {
				// 获取上传状态
						select(e){
							console.log('选择文件：',e)
							uniCloud.uploadFile({
								filePath:e.tempFiles[0].file,
								success(res){
									
								}
							})
						},
						// 获取上传进度
						progress(e){
							console.log('上传进度：',e)
						},
						
						// 上传成功
						success(e){
							console.log(e.tempFilePaths[0])
							console.log('上传成功')
							uni.request({
								method:"POST",
							    url: 'http://192.168.3.4:8081/bohe/aiFood', //仅为示例，并非真实接口地址。
							    data: {
							        img: e.tempFilePaths[0]
							    },
							    success: (res) => {
							        console.log(res.data);
									this.list = res.data.data
							        
							    }
							})
							
						},
						
						// 上传失败
						fail(e){
							console.log('上传失败：',e)
						},
			open(){
				uni.chooseMedia({
				  count: 9,
				  mediaType: ['image','video'],
				  sourceType: ['album', 'camera'],
				  maxDuration: 30,
				  camera: 'back',
				  success(res) {
				    console.log(res.tempFiles)
					
					 // 获取图片Base64编码
					        uni.getFileSystemManager().readFile({
					          filePath: res.tempFiles[0].tempFilePath,
					          encoding: 'base64',
					          success: function(data) {
					            var base64Data = data.data; // 图片Base64编码
					            
								var param = {
									img:base64Data
								}
								console.log(param);
								
					            // 这里可以对Base64编码进行其他处理
								
					          },
					          fail: function(err) {
					            console.log(err);
					          }
					        })
				  }
				})
			}
		}
	}
</script>

<style>
	.btnImg{
		display: flex; justify-content: space-around;align-items: center;
	}
</style>
