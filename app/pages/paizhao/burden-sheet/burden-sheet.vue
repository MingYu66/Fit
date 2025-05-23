<template>
	<view>
		<view class="card-body gui-card-body gui-box-shadow gui-border-b card">
			<view class="" style="display: flex; justify-content: space-around;align-items: center;">
				<view class="btnImg" >
					<text class="gui-color-white mint" style="font-size: 80rpx;">&#xe611;</text>
					配料识别
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
			<view class="" v-for="item in list" style="margin: 5px;">
				
				<view class="gui-td-text gui-bold gui-text-center" style="display: flex;justify-content: center;align-items: center;background-color: aqua;">
					{{item.name}}
				</view>
				<view class="" style="border: 1px solid black;">
					{{item.desc}}
				</view>
				
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
					{
						name:"乙基麦芽酚(演示)",
						desc:"理想食品添加剂, 乙基麦芽酚是一种安全、可靠、用量少、效果显著的食品添加剂，分子式为C7H8O3。作为一种香味改良剂、增香剂，应用越来越广泛，是烟草、食品、饮料、香精、果酒、日用化妆品等良好的香味增效剂，对食品的香味改善和增强具有显著效果，对甜食起着增甜作用，且能延长食品储存期。1970年，乙基麦芽酚已正式被世界卫生组织及联合国粮农组织列入食品添加剂的行列，其凭借增香效果显著、公认的安全性以及广泛的应用性等特点以较快的速度在国内外发展。虽然乙基麦芽酚是麦芽酪的同系物，但因为分子结构发生了微小变化，便使其增香效果达到麦芽酪的3-8倍。"
					}
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
							    url: 'http://192.168.3.4:8081/bohe/aiBurdenSheet', //仅为示例，并非真实接口地址。
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
