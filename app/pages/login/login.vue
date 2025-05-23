<template>
	<view style="padding:50rpx;">
		<view style="height:150rpx;"></view>
		<view class="gui-flex gui-rows gui-justify-content-center">
			<text
				class="login-logo gui-icons gui-color-white gui-block-text gui-border-box gui-bg-black gui-box-shadow gui-box">&#xe60d;
</text>
		</view>
		<view style="margin-top:80rpx;">
			<form @submit="submit">
				<view>
					<text class="gui-text-small gui-color-gray">登录名</text>
				</view>
				<view class="gui-border-b">
					<input type="text" class="gui-form-input" name="username" placeholder="账号" />
				</view>
				<view class="gui-margin-top">
					<text class="gui-text-small gui-color-gray">密码</text>
				</view>
				<view class="gui-border-b">
					<input type="password" class="gui-form-input" name="password" placeholder="密码" />
				</view>
				<view class="gui-margin-top gui-flex gui-rows gui-space-between" hover-class="gui-tap">
					<text class="gui-text gui-color-gray gui-block-text" @tap="forgetPwd">注册</text>

				</view>
				<view style="margin-top:38rpx;">
					<button type="default" class="gui-button gui-bg-black" formType="submit"
						style="border-radius:50rpx;">
						<text class="gui-color-white gui-button-text">登 录</text>
					</button>
				</view>
			</form>
		</view>



	</view>
</template>
<script>
	var graceChecker = require("@/GraceUI5/js/checker.js");
	var graceJS = require('@/GraceUI5/js/grace.js');
	var request = require('@/GraceUI5/js/request/request.js');
	export default {
		data() {
			return {}
		},
		onLoad() {
			var userId = graceJS.getStorage("id")
			if(userId > 0){
				uni.switchTab({
					url:"../switchPages/index"
				})
			}
			
		},
		methods: {
			forgetPwd: function() {

				graceJS.navigate('../register/register');

				console.log('请自行完善代码');
			},
			loginbymsg: function() {
				console.log('请自行完善代码');
			},
			submit: function(e) {
				// 表单数据
				var formData = e.detail.value;
				console.log(formData);
				// 利用 graceUI 的表单验证工具进行验证
				//定义表单规则
				var rule = [{
						name: "username",
						checkType: "string",
						checkRule: "1,50",
						errorMsg: "登录账户输入有误"
					},
					{
						name: "password",
						checkType: "string",
						checkRule: "1,100",
						errorMsg: "登录密码至少1个字符"
					},
				];
				var checkRes = graceChecker.check(formData, rule);
				if (checkRes) {
					request.post("bohe/boheLogin",{data:formData},(res)=>{
						console.log(res.data)
						if(res.data.code == 200 ){
							var user = {user:res.data.data}
							graceJS.setStorage(res.data.data)
							uni.showToast({
								title: "登陆成功",
								icon: "none",
								success() {
									setTimeout(()=>{
										uni.switchTab({
											url:"../switchPages/index"
										})
									},500)
									
								}
							});
						}
						
					})
					

				} else {
					uni.showToast({
						title: graceChecker.error,
						icon: "none"
					});
				}
			}
		}
	}
</script>
<style>
	.login-logo {
		width: 180rpx;
		height: 180rpx;
		font-size: 80rpx;
		text-align: center;
		line-height: 120rpx;
		padding: 30rpx;
		border-radius: 18rpx;
	}

	.other-login-icons {
		width: 88rpx;
		height: 88rpx;
		text-align: center;
		font-size: 70rpx;
		margin: 20rpx;
	}
</style>