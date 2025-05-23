<template>
	<view>
		<!--index.wxml-->
		
		<!-- 选择任务页 -->
		<view v-if="!isClock" class="wrap">
		    <view class="slider">
		        <slider min="1" max="60" show-value activeColor="#FA8072" :value="time" @change="slideChange"></slider>
		    </view>
		    <view class="text">
		        <view class="text_title">请选择一个任务</view>
		        <view class="text_desc">在接下来的{{time}}分钟，开始专注吧</view>
		    </view>
		  
		    <view class="box_cate">
		        <view v-for="item in cateArr" class="cate_item" @click="buttonChange(item.text)" >
		           <view class="cate_img">
		                <image :src="item.icon" ></image>
		            </view>
		            <view class="cate_text">{{item.text}}</view>
		        </view>
		    </view>
		</view>
		
		
		<!-- 番茄计时页 -->
		<view v-if="isClock" class="clock">
		    <view class="clock_text">{{clockText}}中...</view>
		    <view class="count_down">
		        <!-- <canvas  id="circle" class="circle" ></canvas>
		        <canvas  id="circle_active" class="circle_active"></canvas> -->
				<l-circle style="margin-left: 20%;" :max="percent" :percent="mTime">
					<view class="t_c" style="font-size: 26upx;color: #333; text-align: center;">
						<view class="count_text" style="margin-left: -72rpx;margin-top: -76rpx;">{{timeStr}}</view>
						
						
					</view>
				</l-circle>
		    </view>
		    <view class="clock_button">
		        <image src="../../static/pause.png" @click="handlePause" v-if="playShow"></image>
		        <image src="../../static/play.png"  @click="handlePlay" v-else ></image>
		        <image src="../../static/suspend.png"  @click="handleSuspend"></image>
		    </view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				time:'25', //分钟
				        mTime:1500000, //剩余毫秒
				        timeStr:'25:00',
				        rate:'',
				        isClock:false,
				        timer:null,
				        playShow:true, //暂停按钮切换
				        clockText:'', //番茄页任务名称显示
						modelVale: 10,
						percent: 500 / 4000 * 100,
				        cateArr:[
				            {
				                icon:'../../static/work.png',
				                text:'专注'
				            },
							{
							    icon:'../../static/others.png',
							    text:'自定义'
							},
				            {
				                icon:'../../static/relax.png',
				                text:'休息'
				            }
				        ]
			}
		},
		onLoad(){
		        let res=wx.getSystemInfoSync()
		        //750rpx 不同型号自动适配
		        let rate=750/res.windowWidth
				this.rate = rate
		       
		        console.log(444,rate)
		    },
		methods: {
			slideChange(e){
			        console.log(1,e.detail.value)
					this.time = e.detail.value
			        
			    },
			    buttonChange(e){
					
			        const iconList = e
					console.log(iconList)
			        if(iconList==='自定义'){
			            let _this=this
			            uni.showModal({
			                title: '自定义任务',
			                editable:true,
			                placeholderText:'请输入任务名称',
			                confirmColor:'#FA8072',
			                success (res) {
								console.log(res)
			                  if (res.confirm) {
								  _this.clockText = res.content
			                   
			                    _this.saveData()
			                  }
			                }
			              })
			        }else{
						console.log("不是自定义")
			            this.clockText=iconList
			            
			            this.saveData()
			        }
			    },
			    saveData(){
			            this.isClock=true,
			            this.mTime=this.time*60*1000, //ms
			            this.timeStr=parseInt(this.time) >= 10 ? this.time+ ":00" :"0" +this.time + ":00"
						this.percent = this.time*60*1000
						this.dqTime = 0
			        // this.drawCircle()
			        this.drawActive()
			    },
			   
			    drawActive(){
			        let _this = this
			        var timer =setInterval(()=>{
			            let angle = 1.5 + 2*(_this.time*60*1000 - _this.mTime)/(_this.time*60*1000);
			            if(_this.mTime >= 100){
							let currentTime = _this.mTime - 100
							_this.mTime=currentTime
							if(currentTime % 1000 == 0){
							    var timeStr1 = currentTime / 1000;//s
							    var timeStr2 = parseInt(timeStr1 / 60); //m
							    var timeStr3 = (timeStr1 - timeStr2 * 60) >= 10 ? (timeStr1 - timeStr2 * 60) :"0" +  (timeStr1 - timeStr2 * 60);
							    var timeStr2 = timeStr2 >= 10 ? timeStr2:"0" + timeStr2;
							    _this.timeStr=timeStr2 + ":" + timeStr3
							  };
						}else{
			                //计时完成时
			                wx.showModal({
			                    title: '提示',
			                    content:'当前计时已完成，将跳转回主界面',
			                    showCancel:false,
			                    confirmText:'好的',
			                    confirmColor:'#FA8072',
			                    success (res) {
			                      if (res.confirm) {
			                        
			                        _this.timeStr="00:00"
			                        _this.playShow=false
			                        _this.isClock=false
			                        
			                      }
			                    }
			                })
			                clearInterval(timer); 
			            }    
			        },100)
			        _this.timer =timer
			      
			    },
			    // 暂停按钮
			    handlePause(){
			        console.log(99,this.timer)
			        clearInterval(this.timer);
			        this.playShow=false
			        
			    },
			    //播放按钮
			    handlePlay(){
			        this.drawActive()
			        this.playShow=true
			       
			    },
			    //中止按钮
			    handleSuspend(){
			        clearInterval(this.timer);
			        let _this=this
			        wx.showModal({
			            title: '提示',
			            content:'确定中止任务吗？当前计时会立刻停止，并返回主界面',
			            confirmColor:'#FA8072',
			            success (res) {
			              if (res.confirm) {
			                _this.isClock=false,
			                    _this.playShow=true
			              
			              }else if(res.cancel){
			                _this.drawActive()
			              }
			            }
			        })
			    }
		}
	}
</script>

<style>
page{
	background-color: #fef2f1;
}
.wrap{
    margin: 300rpx auto;
}
.slider{
    width: 600rpx;
    margin:150rpx auto;
}
.text{
    text-align: center;
}
.text .text_title{
    font-size: 40rpx;
    height: 60rpx;
    line-height: 60rpx;
}
.text .text_desc{
    font-size: 30rpx;
    height: 50rpx;
    line-height: 50rpx;
}
.box_cate{
    display: flex;
    width: 660rpx;
    flex-wrap: wrap;
    margin:100rpx  auto;
}
.box_cate .cate_item{
    width: 220rpx;
    height: 100rpx;
    text-align: center;
}
.box_cate .cate_item .cate_img{
    height: 100rpx;
}
.box_cate .cate_item .cate_img image{
    height: 100rpx;
    width: 100rpx;
}
.box_cate .cate_item .cate_text{
    height: 40rpx;
}
/* .box_input{
    margin: 100rpx auto;
    text-align: center;
}
.box_input .input1{
    width: 400rpx;
    height: 80rpx;
    margin: auto;
    border-style: soild;
    border-radius: 15rpx;
    box-shadow: 0 0 3px #FA8072;
    background: rgba(250, 128, 114, 0.1)
} */
/* 番茄计时页 */
.clock{
    background-color: #FA8072;
    height: 100vh;
    overflow: hidden;
}
.clock_button{
    display: flex;
    width: 300rpx;
    flex-wrap: wrap;
    margin:auto;
}
.clock_button image{
    width: 50rpx;
    height: 50rpx;
    margin: 50rpx;
}
.count_down{
    /* background-color: royalblue; */
    width: 400rpx;
    height: 400rpx;
    margin:50rpx auto 50rpx auto;
    position: relative;
}
.count_down .circle,.count_down .circle_active{
    position: absolute;
    width: 400rpx;
    height: 400rpx;
}
.clock_text{
    margin-top: 200rpx;
    text-align: center;
    color: #ffffff;
}
.count_down .count_text{
    position: absolute;
    left: 100rpx;
    top: 170rpx;
    width: 200rpx;
    height: 60rpx;
    line-height: 60rpx;
    font-size: 50rpx;
    text-align: center;
    color: #ffffff;
}
</style>
