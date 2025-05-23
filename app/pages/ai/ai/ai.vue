<template>
  <view class="chat-container">
    <!-- 聊天内容区域 -->
    <scroll-view 
      class="chat-list" 
      scroll-y 
      :scroll-into-view="'msg-' + (messages.length-1)"
      :scroll-with-animation="true"
    >
      <view 
        v-for="(item, index) in messages" 
        :key="index" 
        :id="'msg-' + index"
        class="message-container"
      >
        <!-- 时间戳 -->
      <!--  <view class="time-stamp" v-if="showTimeStamp(index)">
          {{ formatTime(item.timestamp) }}
        </view> -->

        <!-- AI消息 -->
        <view class="message-left" v-if="!item.isUser">
          <image class="avatar" src="../../../static/deepseek.png" mode="aspectFit" />
          <view class="bubble-wrapper">
            <view class="bubble ai-bubble">
              <text>{{ item.displayText }}</text>
              <view class="typing-indicator" v-if="item.thinking">
                <view class="dot"></view>
                <view class="dot"></view>
                <view class="dot"></view>
              </view>
            </view>
          </view>
        </view>

        <!-- 用户消息 -->
        <view class="message-right" v-else>
			
			<image class="avatar" src="../../../static/userrimg.png" mode="aspectFit" />
          <view class="bubble-wrapper">
            <view class="bubble user-bubble">
              <text>{{ item.content }}</text>
            </view>
          </view>
        </view>
      </view>
    </scroll-view>

    <!-- 输入区域 -->
    <view class="input-bar">
      <input 
        class="input-box"
        v-model="inputMsg"
        placeholder="输入健身问题..."
        placeholder-class="placeholder-style"
        confirm-type="send"
        @confirm="sendMessage"
      />
      <button 
        class="send-btn" 
        :class="{ 'disabled': loading }" 
        @click="sendMessage"
      >
        <text v-if="!loading">发送</text>
        <image v-else src="/static/loading.gif" class="loading-icon" />
      </button>
    </view>
  </view>
</template>

<script>
	var request = require('@/GraceUI5/js/request/request.js');
	var graceJS = require('@/GraceUI5/js/grace.js');
export default {
  data() {
    return {
      inputMsg: '',
      messages: [],
      loading: false,
      userAvatar: '/static/user-avatar.png' // 从用户信息获取
    }
  },
  methods: {
    // 时间格式化
    formatTime(timestamp) {
      return dayjs(timestamp).format('HH:mm')
    },

    // 判断是否显示时间戳
    showTimeStamp(index) {
      if (index === 0) return true
      const currentTime = this.messages[index].timestamp
      const prevTime = this.messages[index-1].timestamp
      return (currentTime - prevTime) > 300000 // 5分钟
    },

    async sendMessage() {
      if (this.loading || !this.inputMsg.trim()) return
      
      const userMsg = this.inputMsg
      this.inputMsg = ''
      
      // 添加用户消息
      this.messages.push({
        content: userMsg,
        isUser: true,
        timestamp: Date.now()
      })
      
      // 添加AI消息占位
      this.messages.push({
        displayText: '',
        isUser: false,
        thinking: true,
        timestamp: Date.now()
      })

      this.loading = true
      
      try {
       
        request.post('ai/fitness/ask', {data:{
          userMessage: userMsg,
          userId:graceJS.getStorage("id")
        }},(res)=>{
        	console.log(res)
        	
        	// 清除旧的打字效果
        	if (this.currentTyping) {
        	  clearInterval(this.currentTyping);
        	}
        	
        
        	
        // 移除思考状态
        const aiMessage = this.messages[this.messages.length-1]
        aiMessage.thinking = false
        
       // 开始打字效果
       const fullText = res.data.msg;
       console.log(fullText)
       const targetIndex = this.messages.length - 1;
       let charIndex = 0;
        const typeInterval = setInterval(() => {
          if (charIndex < fullText.length) {
            aiMessage.displayText = fullText.substring(0, charIndex + 1)
            charIndex++
          } else {
            clearInterval(typeInterval)
            this.loading = false
          }
        }, 30)
        	
        	
        })
       
        
      } catch (error) {
        this.messages[this.messages.length-1].displayText = '服务暂时不可用，请稍后再试'
        this.loading = false
      }
    }
  }
}
</script>
<style lang="scss" scoped>
.chat-container {
  height: 100vh;
  display: flex;
  flex-direction: column;
  background: #f5f5f5;
}

.chat-list {
  flex: 1;
  // padding: 20rpx;
  background: #f0f0f0;
}

.message-container {
  margin: 30rpx 0;
}

.time-stamp {
  text-align: center;
  color: #999;
  font-size: 24rpx;
  margin: 40rpx 0;
}

.message-left, .message-right {
  display: flex;
  align-items: flex-start;
  margin: 20rpx 0;
  
  .avatar {
    width: 80rpx;
    height: 80rpx;
    border-radius: 8rpx;
  }
}

.message-left {
  flex-direction: row;
  
  .bubble-wrapper {
    max-width: 70%;
    margin-left: 20rpx;
  }
}

.message-right {
  flex-direction: row-reverse;
  display: flex;
  align-items: flex-start;
  .bubble-wrapper {
    max-width: 70%;
    margin-right: 20rpx;
  }
}

.bubble {
  padding: 20rpx 28rpx;
  border-radius: 12rpx;
  font-size: 32rpx;
  line-height: 1.5;
  position: relative;
  word-break: break-word;
}

.ai-bubble {
  background: #fff;
  border-radius: 0 24rpx 24rpx 24rpx;
  box-shadow: 0 4rpx 12rpx rgba(0, 0, 0, 0.05);
  
  &::before {
    content: '';
    position: absolute;
    left: -16rpx;
    top: 0;
    width: 0;
    height: 0;
    border: 16rpx solid transparent;
    border-right-color: #fff;
  }
}

.user-bubble {
  background: #95ec69;
  border-radius: 24rpx 0 24rpx 24rpx;
  color: #000;
  box-shadow: 0 4rpx 12rpx rgba(0, 0, 0, 0.08);
  
  &::after {
    content: '';
    position: absolute;
    right: -16rpx;
    top: 0;
    width: 0;
    height: 0;
    border: 16rpx solid transparent;
    border-left-color: #95ec69;
  }
}

.input-bar {
  display: flex;
  align-items: center;
  padding: 20rpx 30rpx;
  background: #fff;
  border-top: 1rpx solid #eee;
  
  .input-box {
    flex: 1;
    background: #f8f8f8;
    height: 80rpx;
    padding: 0 30rpx;
    border-radius: 40rpx;
    margin-right: 20rpx;
    font-size: 32rpx;
  }
  
  .send-btn {
    width: 140rpx;
    height: 80rpx;
    line-height: 80rpx;
    border-radius: 40rpx;
    background: #07c160;
    color: #fff;
    font-size: 32rpx;
    padding: 0;
    
    &.disabled {
      opacity: 0.6;
    }
    
    .loading-icon {
      width: 40rpx;
      height: 40rpx;
    }
  }
}

.placeholder-style {
  color: #999;
}

/* 打字效果 */
.typing-indicator {
  display: inline-flex;
  align-items: center;
  padding-left: 20rpx;
  
  .dot {
    width: 12rpx;
    height: 12rpx;
    background: #ddd;
    border-radius: 50%;
    margin-right: 8rpx;
    animation: bounce 1.4s infinite ease-in-out;
    
    &:nth-child(2) {
      animation-delay: 0.2s;
    }
    &:nth-child(3) {
      animation-delay: 0.4s;
    }
  }
}

@keyframes bounce {
  0%, 80%, 100% { 
    transform: translateY(0);
  }
  40% {
    transform: translateY(-10rpx);
  }
}
</style>