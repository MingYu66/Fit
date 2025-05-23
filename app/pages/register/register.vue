<template>
	<view style="padding:50rpx;">

		<view class="gui-flex gui-rows gui-justify-content-center">
			<text
				class="login-logo gui-icons gui-color-white gui-block-text gui-border-box gui-bg-black gui-box-shadow gui-box">&#xe60d;</text>
		</view>
		<view style="margin-top:80rpx;">
			<form @submit="submit">
				<view>
					<text class="gui-text-small gui-color-gray">账户</text>
				</view>
				<view class="gui-border-b">
					<input type="text" class="gui-form-input" name="username" placeholder="注册账户" />
				</view>
				<view class="gui-margin-top">
					<text class="gui-text-small gui-color-gray">密码</text>
				</view>
				<view class="gui-border-b">
					<input type="password" class="gui-form-input" name="password" placeholder="密码" />
				</view>
				<view>
					<text class="gui-text-small gui-color-gray">姓名</text>
				</view>
				<view class="gui-border-b">
					<input type="text" class="gui-form-input" name="name" placeholder="姓名" />
				</view>
				<view>
					<text class="gui-text-small gui-color-gray">身高</text>
				</view>
				<view class="gui-border-b">
					<input type="number" class="gui-form-input" name="height" placeholder="cm" />
				</view>

				<view>
					<text class="gui-text-small gui-color-gray">体重</text>
				</view>
				<view class="gui-border-b">
					<input type="number" class="gui-form-input" name="weight" placeholder="KG" />
				</view>

				<view>
					<text class="gui-text-small gui-color-gray">年龄</text>
				</view>
				<view class="gui-border-b">
					<input type="number" class="gui-form-input" name="age" placeholder="年龄" />
				</view>
				<view>
					<text class="gui-text-small gui-color-gray">性别</text>
				</view>
				<view class="gui-border-b">
					<view class="gui-form-body">
						<picker mode="selector" :range="gender" @change="pickerChange">
							<view class="gui-flex gui-rows gui-nowrap gui-space-between gui-align-items-center">
								<text class="gui-text">{{gender[genderIndex]}}</text>
								
							</view>
						</picker>
					</view>
				</view>
				
				<view>
					<text class="gui-text-small gui-color-gray">过敏源/使用目的</text>
					
				</view>
				<view class="gui-border-b">
					<view class="gui-form-body">
						<picker mode="selector" :range="options" @change="pickerChangeMb">
							<view class="gui-flex gui-rows gui-nowrap gui-space-between gui-align-items-center">
								<text class="gui-text">{{options[typeIndex]}}</text>
								
							</view>
						</picker>
					</view>
				</view>
				
				<view style="margin-top:38rpx;">
					<button type="default" class="gui-button gui-bg-black" formType="submit"
						style="border-radius:50rpx;">
						<text class="gui-color-white gui-button-text">注 册</text>
					</button>
				</view>
			</form>
		</view>



	</view>
</template>
<script>
	var graceJS = require('@/GraceUI5/js/grace.js');
	var request = require('@/GraceUI5/js/request/request.js');
	var graceChecker = require("@/GraceUI5/js/checker.js");
	export default {
		data() {
			return {
				gender: ['请选择性别', '男', '女'],
				options: ['减脂', '增肌', '塑形','控糖','增重','海鲜过敏', '芒果过敏','奶制品过敏',],
				// options: ['动物蛋白过敏', '海鲜过敏', '芒果过敏','奶制品过敏','减脂', '增肌', '塑形','控糖','增重'],
				//病症类型
				      // options: [{
				      //     value: '减脂',
				      //     label: '减脂'
				      //   }, {
				      //     value: '增肌',
				      //     label: '增肌'
				      //   }, {
				      //     value: '塑形',
				      //     label: '塑形'
				      //   }, {
				      //     value: '控糖',
				      //     label: '控糖'
				      //   }, {
				      //     value: '增重',
				      //     label: '增重'
				      //   }],
				genderIndex: 0,
				typeIndex: 0
			}
		},
		methods: {
			forgetPwd: function() {
				console.log('请自行完善代码');
			},
			loginbymsg: function() {
				console.log('请自行完善代码');
			},
			// picker 切换
			pickerChange: function(e) {
				console.log(e);
				this.genderIndex = e.detail.value;
			},
			// picker 切换
			pickerChangeMb: function(e) {
				console.log(e);
				this.typeIndex = e.detail.value;
			},
			submit: function(e) {
				// 表单数据
				var formData = e.detail.value;
				
				formData.sex = this.genderIndex == 0 ? 1 : this.genderIndex 
				formData.type = this.options[this.typeIndex] 
				
				console.log(formData);
			
				// 利用 graceUI 的表单验证工具进行验证
				//定义表单规则
				var rule = [{
						name: "username",
						checkType: "string",
						checkRule: "1,10",
						errorMsg: "账户输入有误"
					},
					{
						name: "password",
						checkType: "string",
						checkRule: "1,20",
						errorMsg: "密码至少1个字符"
					}
				];
				var checkRes = graceChecker.check(formData, rule);
				if (checkRes) {
					
					request.post("bohe/add",{data:formData},(res)=>{
						console.log(res.data)
						if(res.data.code == 200 ){
							var user = {user:res.data.data}
							graceJS.setStorage(res.data.data)
							uni.showToast({
								title: "注册成功",
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
					
					// 根据项目需要完成数据验证工作
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