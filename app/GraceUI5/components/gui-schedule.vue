<template>
	<view class="gui-schedule-wrap">
		<view class="gui-flex gui-rows gui-space-between gui-align-items-center">
			<picker  mode="date" 
			:value="currentDayIn" 
			:start="startDate" 
			:end="endDate" 
			@change="selectDate">
				<text 
				class="gui-schedule-header-date gui-icons">{{cYear}} 年 {{cMonthStr}} 月 &#xe603;</text>
			</picker>
			<text class="gui-border gui-schedule-today" @tap="gotoToday">返回今天</text>
		</view>
		<view class="gui-flex gui-rows gui-nowrap">
			<text class="gui-schedule-weeks gui-color-gray gui-block-text" 
			v-for="(item, index) in weeks" :key="index">{{item}}</text>
		</view>
		<view class="gui-flex gui-rows gui-wrap">
			<view class="gui-schedule-7item gui-flex gui-rows gui-justify-content-center"
			v-for="(item, index) in days" :key="index">
				<view 
				class="gui-date-ditems gui-flex gui-columns gui-justify-content-center" 
				v-if="item != ''" 
				:style="{
					backgroundColor:currentDayIn == cYear+'-'+cMonthStr+'-'+ item.date 
					? activeBgColor : bgColor
				}" 
				@click="chooseDate(cYear+'-'+cMonthStr+'-'+item.date, item.date)">
					<text class="gui-date-day gui-block-text" 
					:class="[currentDayIn == (cYear+'-'+cMonthStr+'-'+item.date) ? 
					'gui-d-current-txt' : '']">{{item.date}}</text>
					<text class="gui-date-nl gui-block-text" v-if="isLunar" 
					:class="[currentDayIn == (cYear+'-'+cMonthStr+'-'+item.date) ? 
					'gui-d-current-txt' : '']">{{item.nl}}</text>
					<view class="gui-schedule-point" 
					v-if="item.haveSe" 
					:style="{backgroundColor:pointColor}"></view>
				</view>
				<view class="gui-date-ditems" v-else style="background-color:none;"></view>
			</view>
		</view>
		<view class="gui-border-b gui-schedule-line"></view>
		<view class="gui-schedule-time-list-wrap">
			<view class="gui-schedule-time-list gui-flex gui-rows gui-nowrap"
			v-for="(item, index) in hours" :key="index">
				<text class="gui-schedule-timer gui-block-text gui-color-gray">{{item}}:00</text>
				<view class="gui-border-b gui-schedule-body gui-flex gui-columns">
					<text class="gui-schedules gui-block-text" 
					v-for="(schedule, idx) in schedulesIn[index]" :key="idx" 
					@tap="scheduleTap" :data-id="schedule.id" 
					:style="{
						backgroundColor:schedule.bgColor, 
						color:schedule.color}">{{schedule.content}}</text>
				</view>
			</view>
		</view>
	</view>
</template>
<script>
var guiCalendar = {

  /**
   * 农历1900-2100的润大小信息表
   * @Array Of Property
   * @return Hex
   */
  lunarInfo:[0x04bd8,0x04ae0,0x0a570,0x054d5,0x0d260,0x0d950,0x16554,0x056a0,0x09ad0,0x055d2,//1900-1909
    0x04ae0,0x0a5b6,0x0a4d0,0x0d250,0x1d255,0x0b540,0x0d6a0,0x0ada2,0x095b0,0x14977,//1910-1919
    0x04970,0x0a4b0,0x0b4b5,0x06a50,0x06d40,0x1ab54,0x02b60,0x09570,0x052f2,0x04970,//1920-1929
    0x06566,0x0d4a0,0x0ea50,0x06e95,0x05ad0,0x02b60,0x186e3,0x092e0,0x1c8d7,0x0c950,//1930-1939
    0x0d4a0,0x1d8a6,0x0b550,0x056a0,0x1a5b4,0x025d0,0x092d0,0x0d2b2,0x0a950,0x0b557,//1940-1949
    0x06ca0,0x0b550,0x15355,0x04da0,0x0a5b0,0x14573,0x052b0,0x0a9a8,0x0e950,0x06aa0,//1950-1959
    0x0aea6,0x0ab50,0x04b60,0x0aae4,0x0a570,0x05260,0x0f263,0x0d950,0x05b57,0x056a0,//1960-1969
    0x096d0,0x04dd5,0x04ad0,0x0a4d0,0x0d4d4,0x0d250,0x0d558,0x0b540,0x0b6a0,0x195a6,//1970-1979
    0x095b0,0x049b0,0x0a974,0x0a4b0,0x0b27a,0x06a50,0x06d40,0x0af46,0x0ab60,0x09570,//1980-1989
    0x04af5,0x04970,0x064b0,0x074a3,0x0ea50,0x06b58,0x055c0,0x0ab60,0x096d5,0x092e0,//1990-1999
    0x0c960,0x0d954,0x0d4a0,0x0da50,0x07552,0x056a0,0x0abb7,0x025d0,0x092d0,0x0cab5,//2000-2009
    0x0a950,0x0b4a0,0x0baa4,0x0ad50,0x055d9,0x04ba0,0x0a5b0,0x15176,0x052b0,0x0a930,//2010-2019
    0x07954,0x06aa0,0x0ad50,0x05b52,0x04b60,0x0a6e6,0x0a4e0,0x0d260,0x0ea65,0x0d530,//2020-2029
    0x05aa0,0x076a3,0x096d0,0x04afb,0x04ad0,0x0a4d0,0x1d0b6,0x0d250,0x0d520,0x0dd45,//2030-2039
    0x0b5a0,0x056d0,0x055b2,0x049b0,0x0a577,0x0a4b0,0x0aa50,0x1b255,0x06d20,0x0ada0,//2040-2049
    /**Add By JJonline@JJonline.Cn**/
    0x14b63,0x09370,0x049f8,0x04970,0x064b0,0x168a6,0x0ea50, 0x06b20,0x1a6c4,0x0aae0,//2050-2059
    0x0a2e0,0x0d2e3,0x0c960,0x0d557,0x0d4a0,0x0da50,0x05d55,0x056a0,0x0a6d0,0x055d4,//2060-2069
    0x052d0,0x0a9b8,0x0a950,0x0b4a0,0x0b6a6,0x0ad50,0x055a0,0x0aba4,0x0a5b0,0x052b0,//2070-2079
    0x0b273,0x06930,0x07337,0x06aa0,0x0ad50,0x14b55,0x04b60,0x0a570,0x054e4,0x0d160,//2080-2089
    0x0e968,0x0d520,0x0daa0,0x16aa6,0x056d0,0x04ae0,0x0a9d4,0x0a2d0,0x0d150,0x0f252,//2090-2099
    0x0d520],//2100

  /**
   * 公历每个月份的天数普通表
   * @Array Of Property
   * @return Number
   */
  solarMonth:[31,28,31,30,31,30,31,31,30,31,30,31],

  /**
   * 天干地支之天干速查表
   * @Array Of Property trans["甲","乙","丙","丁","戊","己","庚","辛","壬","癸"]
   * @return Cn string
   */
  Gan:["\u7532","\u4e59","\u4e19","\u4e01","\u620a","\u5df1","\u5e9a","\u8f9b","\u58ec","\u7678"],

  /**
   * 天干地支之地支速查表
   * @Array Of Property
   * @trans["子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"]
   * @return Cn string
   */
  Zhi:["\u5b50","\u4e11","\u5bc5","\u536f","\u8fb0","\u5df3","\u5348","\u672a","\u7533","\u9149","\u620c","\u4ea5"],

  /**
   * 天干地支之地支速查表<=>生肖
   * @Array Of Property
   * @trans["鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪"]
   * @return Cn string
   */
  Animals:["\u9f20","\u725b","\u864e","\u5154","\u9f99","\u86c7","\u9a6c","\u7f8a","\u7334","\u9e21","\u72d7","\u732a"],

  /**
   * 24节气速查表
   * @Array Of Property
   * @trans["小寒","大寒","立春","雨水","惊蛰","春分","清明","谷雨","立夏","小满","芒种","夏至","小暑","大暑","立秋","处暑","白露","秋分","寒露","霜降","立冬","小雪","大雪","冬至"]
   * @return Cn string
   */
  solarTerm:["\u5c0f\u5bd2","\u5927\u5bd2","\u7acb\u6625","\u96e8\u6c34","\u60ca\u86f0","\u6625\u5206","\u6e05\u660e","\u8c37\u96e8","\u7acb\u590f","\u5c0f\u6ee1","\u8292\u79cd","\u590f\u81f3","\u5c0f\u6691","\u5927\u6691","\u7acb\u79cb","\u5904\u6691","\u767d\u9732","\u79cb\u5206","\u5bd2\u9732","\u971c\u964d","\u7acb\u51ac","\u5c0f\u96ea","\u5927\u96ea","\u51ac\u81f3"],

  /**
   * 1900-2100各年的24节气日期速查表
   * @Array Of Property
   * @return 0x string For splice
   */
  sTermInfo:['9778397bd097c36b0b6fc9274c91aa','97b6b97bd19801ec9210c965cc920e','97bcf97c3598082c95f8c965cc920f',
    '97bd0b06bdb0722c965ce1cfcc920f','b027097bd097c36b0b6fc9274c91aa','97b6b97bd19801ec9210c965cc920e',
    '97bcf97c359801ec95f8c965cc920f','97bd0b06bdb0722c965ce1cfcc920f','b027097bd097c36b0b6fc9274c91aa',
    '97b6b97bd19801ec9210c965cc920e','97bcf97c359801ec95f8c965cc920f','97bd0b06bdb0722c965ce1cfcc920f',
    'b027097bd097c36b0b6fc9274c91aa','9778397bd19801ec9210c965cc920e','97b6b97bd19801ec95f8c965cc920f',
    '97bd09801d98082c95f8e1cfcc920f','97bd097bd097c36b0b6fc9210c8dc2','9778397bd197c36c9210c9274c91aa',
    '97b6b97bd19801ec95f8c965cc920e','97bd09801d98082c95f8e1cfcc920f','97bd097bd097c36b0b6fc9210c8dc2',
    '9778397bd097c36c9210c9274c91aa','97b6b97bd19801ec95f8c965cc920e','97bcf97c3598082c95f8e1cfcc920f',
    '97bd097bd097c36b0b6fc9210c8dc2','9778397bd097c36c9210c9274c91aa','97b6b97bd19801ec9210c965cc920e',
    '97bcf97c3598082c95f8c965cc920f','97bd097bd097c35b0b6fc920fb0722','9778397bd097c36b0b6fc9274c91aa',
    '97b6b97bd19801ec9210c965cc920e','97bcf97c3598082c95f8c965cc920f','97bd097bd097c35b0b6fc920fb0722',
    '9778397bd097c36b0b6fc9274c91aa','97b6b97bd19801ec9210c965cc920e','97bcf97c359801ec95f8c965cc920f',
    '97bd097bd097c35b0b6fc920fb0722','9778397bd097c36b0b6fc9274c91aa','97b6b97bd19801ec9210c965cc920e',
    '97bcf97c359801ec95f8c965cc920f','97bd097bd097c35b0b6fc920fb0722','9778397bd097c36b0b6fc9274c91aa',
    '97b6b97bd19801ec9210c965cc920e','97bcf97c359801ec95f8c965cc920f','97bd097bd07f595b0b6fc920fb0722',
    '9778397bd097c36b0b6fc9210c8dc2','9778397bd19801ec9210c9274c920e','97b6b97bd19801ec95f8c965cc920f',
    '97bd07f5307f595b0b0bc920fb0722','7f0e397bd097c36b0b6fc9210c8dc2','9778397bd097c36c9210c9274c920e',
    '97b6b97bd19801ec95f8c965cc920f','97bd07f5307f595b0b0bc920fb0722','7f0e397bd097c36b0b6fc9210c8dc2',
    '9778397bd097c36c9210c9274c91aa','97b6b97bd19801ec9210c965cc920e','97bd07f1487f595b0b0bc920fb0722',
    '7f0e397bd097c36b0b6fc9210c8dc2','9778397bd097c36b0b6fc9274c91aa','97b6b97bd19801ec9210c965cc920e',
    '97bcf7f1487f595b0b0bb0b6fb0722','7f0e397bd097c35b0b6fc920fb0722','9778397bd097c36b0b6fc9274c91aa',
    '97b6b97bd19801ec9210c965cc920e','97bcf7f1487f595b0b0bb0b6fb0722','7f0e397bd097c35b0b6fc920fb0722',
    '9778397bd097c36b0b6fc9274c91aa','97b6b97bd19801ec9210c965cc920e','97bcf7f1487f531b0b0bb0b6fb0722',
    '7f0e397bd097c35b0b6fc920fb0722','9778397bd097c36b0b6fc9274c91aa','97b6b97bd19801ec9210c965cc920e',
    '97bcf7f1487f531b0b0bb0b6fb0722','7f0e397bd07f595b0b6fc920fb0722','9778397bd097c36b0b6fc9274c91aa',
    '97b6b97bd19801ec9210c9274c920e','97bcf7f0e47f531b0b0bb0b6fb0722','7f0e397bd07f595b0b0bc920fb0722',
    '9778397bd097c36b0b6fc9210c91aa','97b6b97bd197c36c9210c9274c920e','97bcf7f0e47f531b0b0bb0b6fb0722',
    '7f0e397bd07f595b0b0bc920fb0722','9778397bd097c36b0b6fc9210c8dc2','9778397bd097c36c9210c9274c920e',
    '97b6b7f0e47f531b0723b0b6fb0722','7f0e37f5307f595b0b0bc920fb0722','7f0e397bd097c36b0b6fc9210c8dc2',
    '9778397bd097c36b0b70c9274c91aa','97b6b7f0e47f531b0723b0b6fb0721','7f0e37f1487f595b0b0bb0b6fb0722',
    '7f0e397bd097c35b0b6fc9210c8dc2','9778397bd097c36b0b6fc9274c91aa','97b6b7f0e47f531b0723b0b6fb0721',
    '7f0e27f1487f595b0b0bb0b6fb0722','7f0e397bd097c35b0b6fc920fb0722','9778397bd097c36b0b6fc9274c91aa',
    '97b6b7f0e47f531b0723b0b6fb0721','7f0e27f1487f531b0b0bb0b6fb0722','7f0e397bd097c35b0b6fc920fb0722',
    '9778397bd097c36b0b6fc9274c91aa','97b6b7f0e47f531b0723b0b6fb0721','7f0e27f1487f531b0b0bb0b6fb0722',
    '7f0e397bd097c35b0b6fc920fb0722','9778397bd097c36b0b6fc9274c91aa','97b6b7f0e47f531b0723b0b6fb0721',
    '7f0e27f1487f531b0b0bb0b6fb0722','7f0e397bd07f595b0b0bc920fb0722','9778397bd097c36b0b6fc9274c91aa',
    '97b6b7f0e47f531b0723b0787b0721','7f0e27f0e47f531b0b0bb0b6fb0722','7f0e397bd07f595b0b0bc920fb0722',
    '9778397bd097c36b0b6fc9210c91aa','97b6b7f0e47f149b0723b0787b0721','7f0e27f0e47f531b0723b0b6fb0722',
    '7f0e397bd07f595b0b0bc920fb0722','9778397bd097c36b0b6fc9210c8dc2','977837f0e37f149b0723b0787b0721',
    '7f07e7f0e47f531b0723b0b6fb0722','7f0e37f5307f595b0b0bc920fb0722','7f0e397bd097c35b0b6fc9210c8dc2',
    '977837f0e37f14998082b0787b0721','7f07e7f0e47f531b0723b0b6fb0721','7f0e37f1487f595b0b0bb0b6fb0722',
    '7f0e397bd097c35b0b6fc9210c8dc2','977837f0e37f14998082b0787b06bd','7f07e7f0e47f531b0723b0b6fb0721',
    '7f0e27f1487f531b0b0bb0b6fb0722','7f0e397bd097c35b0b6fc920fb0722','977837f0e37f14998082b0787b06bd',
    '7f07e7f0e47f531b0723b0b6fb0721','7f0e27f1487f531b0b0bb0b6fb0722','7f0e397bd097c35b0b6fc920fb0722',
    '977837f0e37f14998082b0787b06bd','7f07e7f0e47f531b0723b0b6fb0721','7f0e27f1487f531b0b0bb0b6fb0722',
    '7f0e397bd07f595b0b0bc920fb0722','977837f0e37f14998082b0787b06bd','7f07e7f0e47f531b0723b0b6fb0721',
    '7f0e27f1487f531b0b0bb0b6fb0722','7f0e397bd07f595b0b0bc920fb0722','977837f0e37f14998082b0787b06bd',
    '7f07e7f0e47f149b0723b0787b0721','7f0e27f0e47f531b0b0bb0b6fb0722','7f0e397bd07f595b0b0bc920fb0722',
    '977837f0e37f14998082b0723b06bd','7f07e7f0e37f149b0723b0787b0721','7f0e27f0e47f531b0723b0b6fb0722',
    '7f0e397bd07f595b0b0bc920fb0722','977837f0e37f14898082b0723b02d5','7ec967f0e37f14998082b0787b0721',
    '7f07e7f0e47f531b0723b0b6fb0722','7f0e37f1487f595b0b0bb0b6fb0722','7f0e37f0e37f14898082b0723b02d5',
    '7ec967f0e37f14998082b0787b0721','7f07e7f0e47f531b0723b0b6fb0722','7f0e37f1487f531b0b0bb0b6fb0722',
    '7f0e37f0e37f14898082b0723b02d5','7ec967f0e37f14998082b0787b06bd','7f07e7f0e47f531b0723b0b6fb0721',
    '7f0e37f1487f531b0b0bb0b6fb0722','7f0e37f0e37f14898082b072297c35','7ec967f0e37f14998082b0787b06bd',
    '7f07e7f0e47f531b0723b0b6fb0721','7f0e27f1487f531b0b0bb0b6fb0722','7f0e37f0e37f14898082b072297c35',
    '7ec967f0e37f14998082b0787b06bd','7f07e7f0e47f531b0723b0b6fb0721','7f0e27f1487f531b0b0bb0b6fb0722',
    '7f0e37f0e366aa89801eb072297c35','7ec967f0e37f14998082b0787b06bd','7f07e7f0e47f149b0723b0787b0721',
    '7f0e27f1487f531b0b0bb0b6fb0722','7f0e37f0e366aa89801eb072297c35','7ec967f0e37f14998082b0723b06bd',
    '7f07e7f0e47f149b0723b0787b0721','7f0e27f0e47f531b0723b0b6fb0722','7f0e37f0e366aa89801eb072297c35',
    '7ec967f0e37f14998082b0723b06bd','7f07e7f0e37f14998083b0787b0721','7f0e27f0e47f531b0723b0b6fb0722',
    '7f0e37f0e366aa89801eb072297c35','7ec967f0e37f14898082b0723b02d5','7f07e7f0e37f14998082b0787b0721',
    '7f07e7f0e47f531b0723b0b6fb0722','7f0e36665b66aa89801e9808297c35','665f67f0e37f14898082b0723b02d5',
    '7ec967f0e37f14998082b0787b0721','7f07e7f0e47f531b0723b0b6fb0722','7f0e36665b66a449801e9808297c35',
    '665f67f0e37f14898082b0723b02d5','7ec967f0e37f14998082b0787b06bd','7f07e7f0e47f531b0723b0b6fb0721',
    '7f0e36665b66a449801e9808297c35','665f67f0e37f14898082b072297c35','7ec967f0e37f14998082b0787b06bd',
    '7f07e7f0e47f531b0723b0b6fb0721','7f0e26665b66a449801e9808297c35','665f67f0e37f1489801eb072297c35',
    '7ec967f0e37f14998082b0787b06bd','7f07e7f0e47f531b0723b0b6fb0721','7f0e27f1487f531b0b0bb0b6fb0722'],

  /**
   * 数字转中文速查表
   * @Array Of Property
   * @trans ['日','一','二','三','四','五','六','七','八','九','十']
   * @return Cn string
   */
  nStr1:["\u65e5","\u4e00","\u4e8c","\u4e09","\u56db","\u4e94","\u516d","\u4e03","\u516b","\u4e5d","\u5341"],

  /**
   * 日期转农历称呼速查表
   * @Array Of Property
   * @trans ['初','十','廿','卅']
   * @return Cn string
   */
  nStr2:["\u521d","\u5341","\u5eff","\u5345"],

  /**
   * 月份转农历称呼速查表
   * @Array Of Property
   * @trans ['正','一','二','三','四','五','六','七','八','九','十','冬','腊']
   * @return Cn string
   */
  nStr3:["\u6b63","\u4e8c","\u4e09","\u56db","\u4e94","\u516d","\u4e03","\u516b","\u4e5d","\u5341","\u51ac","\u814a"],

  /**
   * 返回农历y年一整年的总天数
   * @param lunar Year
   * @return Number
   * @eg:var count = this.lYearDays(1987) ;//count=387
   */
  lYearDays:function(y) {
    var i, sum = 348;
    for(i=0x8000; i>0x8; i>>=1) { sum += (this.lunarInfo[y-1900] & i)? 1: 0; }
    return(sum+this.leapDays(y));
  },

  /**
   * 返回农历y年闰月是哪个月；若y年没有闰月 则返回0
   * @param lunar Year
   * @return Number (0-12)
   * @eg:var leapMonth = this.leapMonth(1987) ;//leapMonth=6
   */
  leapMonth:function(y) { //闰字编码 \u95f0
    return(this.lunarInfo[y-1900] & 0xf);
  },

  /**
   * 返回农历y年闰月的天数 若该年没有闰月则返回0
   * @param lunar Year
   * @return Number (0、29、30)
   * @eg:var leapMonthDay = this.leapDays(1987) ;//leapMonthDay=29
   */
  leapDays:function(y) {
    if(this.leapMonth(y))  {
      return((this.lunarInfo[y-1900] & 0x10000)? 30: 29);
    }
    return(0);
  },

  /**
   * 返回农历y年m月（非闰月）的总天数，计算m为闰月时的天数请使用leapDays方法
   * @param lunar Year
   * @return Number (-1、29、30)
   * @eg:var MonthDay = this.monthDays(1987,9) ;//MonthDay=29
   */
  monthDays:function(y,m) {
    if(m>12 || m<1) {return -1}//月份参数从1至12，参数错误返回-1
    return( (this.lunarInfo[y-1900] & (0x10000>>m))? 30: 29 );
  },

  /**
   * 返回公历(!)y年m月的天数
   * @param solar Year
   * @return Number (-1、28、29、30、31)
   * @eg:var solarMonthDay = this.leapDays(1987) ;//solarMonthDay=30
   */
  solarDays:function(y,m) {
    if(m>12 || m<1) {return -1} //若参数错误 返回-1
    var ms = m-1;
    if(ms==1) { //2月份的闰平规律测算后确认返回28或29
      return(((y%4 == 0) && (y%100 != 0) || (y%400 == 0))? 29: 28);
    }else {
      return(this.solarMonth[ms]);
    }
  },

  /**
   * 农历年份转换为干支纪年
   * @param  lYear 农历年的年份数
   * @return Cn string
   */
  toGanZhiYear:function(lYear) {
    var ganKey = (lYear - 3) % 10;
    var zhiKey = (lYear - 3) % 12;
    if(ganKey == 0) ganKey = 10;//如果余数为0则为最后一个天干
    if(zhiKey == 0) zhiKey = 12;//如果余数为0则为最后一个地支
    return this.Gan[ganKey-1] + this.Zhi[zhiKey-1];

  },

  /**
   * 公历月、日判断所属星座
   * @param  cMonth [description]
   * @param  cDay [description]
   * @return Cn string
   */
  toAstro:function(cMonth,cDay) {
    var s   = "\u9b54\u7faf\u6c34\u74f6\u53cc\u9c7c\u767d\u7f8a\u91d1\u725b\u53cc\u5b50\u5de8\u87f9\u72ee\u5b50\u5904\u5973\u5929\u79e4\u5929\u874e\u5c04\u624b\u9b54\u7faf";
    var arr = [20,19,21,21,21,22,23,23,23,23,22,22];
    return s.substr(cMonth*2 - (cDay < arr[cMonth-1] ? 2 : 0),2) + "\u5ea7";//座
  },

  /**
   * 传入offset偏移量返回干支
   * @param offset 相对甲子的偏移量
   * @return Cn string
   */
  toGanZhi:function(offset) {
    return this.Gan[offset%10] + this.Zhi[offset%12];
  },

  /**
   * 传入公历(!)y年获得该年第n个节气的公历日期
   * @param y公历年(1900-2100)；n二十四节气中的第几个节气(1~24)；从n=1(小寒)算起
   * @return day Number
   * @eg:var _24 = this.getTerm(1987,3) ;//_24=4;意即1987年2月4日立春
   */
  getTerm:function(y,n) {
    if(y<1900 || y>2100) {return -1;}
    if(n<1 || n>24) {return -1;}
    var _table = this.sTermInfo[y-1900];
    var _info = [
      parseInt('0x'+_table.substr(0,5)).toString() ,
      parseInt('0x'+_table.substr(5,5)).toString(),
      parseInt('0x'+_table.substr(10,5)).toString(),
      parseInt('0x'+_table.substr(15,5)).toString(),
      parseInt('0x'+_table.substr(20,5)).toString(),
      parseInt('0x'+_table.substr(25,5)).toString()
    ];
    var _calday = [
      _info[0].substr(0,1),
      _info[0].substr(1,2),
      _info[0].substr(3,1),
      _info[0].substr(4,2),

      _info[1].substr(0,1),
      _info[1].substr(1,2),
      _info[1].substr(3,1),
      _info[1].substr(4,2),

      _info[2].substr(0,1),
      _info[2].substr(1,2),
      _info[2].substr(3,1),
      _info[2].substr(4,2),

      _info[3].substr(0,1),
      _info[3].substr(1,2),
      _info[3].substr(3,1),
      _info[3].substr(4,2),

      _info[4].substr(0,1),
      _info[4].substr(1,2),
      _info[4].substr(3,1),
      _info[4].substr(4,2),

      _info[5].substr(0,1),
      _info[5].substr(1,2),
      _info[5].substr(3,1),
      _info[5].substr(4,2),
    ];
    return parseInt(_calday[n-1]);
  },

  /**
   * 传入农历数字月份返回汉语通俗表示法
   * @param lunar month
   * @return Cn string
   * @eg:var cnMonth = this.toChinaMonth(12) ;//cnMonth='腊月'
   */
  toChinaMonth:function(m) { // 月 => \u6708
    if(m>12 || m<1) {return -1} //若参数错误 返回-1
    var s = this.nStr3[m-1];
    s+= "\u6708";//加上月字
    return s;
  },

  /**
   * 传入农历日期数字返回汉字表示法
   * @param lunar day
   * @return Cn string
   * @eg:var cnDay = this.toChinaDay(21) ;//cnMonth='廿一'
   */
  toChinaDay:function(d){ //日 => \u65e5
    var s;
    switch (d) {
      case 10:
        s = '\u521d\u5341'; break;
      case 20:
        s = '\u4e8c\u5341'; break;
        break;
      case 30:
        s = '\u4e09\u5341'; break;
        break;
      default :
        s = this.nStr2[Math.floor(d/10)];
        s += this.nStr1[d%10];
    }
    return(s);
  },

  /**
   * 年份转生肖[!仅能大致转换] => 精确划分生肖分界线是“立春”
   * @param y year
   * @return Cn string
   * @eg:var animal = this.getAnimal(1987) ;//animal='兔'
   */
  getAnimal: function(y) {
    return this.Animals[(y - 4) % 12]
  },

  /**
   * 传入阳历年月日获得详细的公历、农历object信息 <=>JSON
   * @param y  solar year
   * @param m  solar month
   * @param d  solar day
   * @return JSON object
   * @eg:console.log(this.solar2lunar(1987,11,01));
   */
  solar2lunar:function (y,m,d) { //参数区间1900.1.31~2100.12.31
    if(y<1900 || y>2100) {return -1;}//年份限定、上限
    if(y==1900&&m==1&&d<31) {return -1;}//下限
    if(!y) { //未传参  获得当天
      var objDate = new Date();
    }else {
      var objDate = new Date(y,parseInt(m)-1,d)
    }
    var i, leap=0, temp=0;
    // 修正ymd参数
    // var y = objDate.getFullYear(),m = objDate.getMonth()+1,d = objDate.getDate();
    var offset   = (Date.UTC(objDate.getFullYear(),objDate.getMonth(),objDate.getDate()) - Date.UTC(1900,0,31))/86400000;
    for(i=1900; i<2101 && offset>0; i++) { temp=this.lYearDays(i); offset-=temp; }
    if(offset<0) { offset+=temp; i--; }

    //是否今天
    var isTodayObj = new Date(),isToday=false;
    if(isTodayObj.getFullYear()==y && isTodayObj.getMonth()+1==m && isTodayObj.getDate()==d) {
      isToday = true;
    }
    //星期几
    var nWeek = objDate.getDay(),cWeek = this.nStr1[nWeek];
    if(nWeek==0) {nWeek =7;}//数字表示周几顺应天朝周一开始的惯例
    //农历年
    var year = i;

    var leap = this.leapMonth(i); //闰哪个月
    var isLeap = false;

    //效验闰月
    for(i=1; i<13 && offset>0; i++) {
      //闰月
      if(leap>0 && i==(leap+1) && isLeap==false){
        --i;
        isLeap = true; temp = this.leapDays(year); //计算农历闰月天数
      }
      else{
        temp = this.monthDays(year, i);//计算农历普通月天数
      }
      //解除闰月
      if(isLeap==true && i==(leap+1)) { isLeap = false; }
      offset -= temp;
    }

    if(offset==0 && leap>0 && i==leap+1)
      if(isLeap){
        isLeap = false;
      }else{
        isLeap = true; --i;
      }
    if(offset<0){ offset += temp; --i; }
    //农历月
    var month   = i;
    //农历日
    var day     = offset + 1;

    //天干地支处理
    var sm      =   m-1;
    var gzY     =   this.toGanZhiYear(year);

    //月柱 1900年1月小寒以前为 丙子月(60进制12)
    var firstNode   = this.getTerm(year,(m*2-1));//返回当月「节」为几日开始
    var secondNode  = this.getTerm(year,(m*2));//返回当月「节」为几日开始

    //依据12节气修正干支月
    var gzM     =   this.toGanZhi((y-1900)*12+m+11);
    if(d>=firstNode) {
      gzM     =   this.toGanZhi((y-1900)*12+m+12);
    }

    //传入的日期的节气与否
    var isTerm = false;
    var Term   = null;
    if(firstNode==d) {
      isTerm  = true;
      Term    = this.solarTerm[m*2-2];
    }
    if(secondNode==d) {
      isTerm  = true;
      Term    = this.solarTerm[m*2-1];
    }
    //日柱 当月一日与 1900/1/1 相差天数
    var dayCyclical = Date.UTC(y,sm,1,0,0,0,0)/86400000+25567+10;
    var gzD = this.toGanZhi(dayCyclical+d-1);
    //该日期所属的星座
    var astro = this.toAstro(m,d);

    return {'lYear':year,'lMonth':month,'lDay':day,'Animal':this.getAnimal(year),'IMonthCn':(isLeap?"\u95f0":'')+this.toChinaMonth(month),'IDayCn':this.toChinaDay(day),'cYear':y,'cMonth':m,'cDay':d,'gzYear':gzY,'gzMonth':gzM,'gzDay':gzD,'isToday':isToday,'isLeap':isLeap,'nWeek':nWeek,'ncWeek':"\u661f\u671f"+cWeek,'isTerm':isTerm,'Term':Term,'astro':astro};
  },

  /**
   * 传入农历年月日以及传入的月份是否闰月获得详细的公历、农历object信息 <=>JSON
   * @param y  lunar year
   * @param m  lunar month
   * @param d  lunar day
   * @param isLeapMonth  lunar month is leap or not.[如果是农历闰月第四个参数赋值true即可]
   * @return JSON object
   * @eg:console.log(this.lunar2solar(1987,9,10));
   */
	lunar2solar:function(y,m,d,isLeapMonth) {
		//参数区间1900.1.31~2100.12.1
		var isLeapMonth = !!isLeapMonth;
		var leapOffset  = 0;
		var leapMonth   = this.leapMonth(y);
		var leapDay     = this.leapDays(y);
		if(isLeapMonth&&(leapMonth!=m)) {return -1;}//传参要求计算该闰月公历 但该年得出的闰月与传参的月份并不同
		if(y==2100&&m==12&&d>1 || y==1900&&m==1&&d<31) {return -1;}//超出了最大极限值
		var day  = this.monthDays(y,m);
		var _day = day;
		//bugFix 2016-9-25
		//if month is leap, _day use leapDays method
		if(isLeapMonth) {
		  _day = this.leapDays(y,m);
		}
		if(y < 1900 || y > 2100 || d > _day) {return -1;}//参数合法性效验

		//计算农历的时间差
		var offset = 0;
		for(var i=1900;i<y;i++) {
		  offset+=this.lYearDays(i);
		}
		var leap = 0,isAdd= false;
		for(var i=1;i<m;i++) {
		  leap = this.leapMonth(y);
		  if(!isAdd) {//处理闰月
			if(leap<=i && leap>0) {
			  offset+=this.leapDays(y);isAdd = true;
			}
		  }
		  offset+=this.monthDays(y,i);
		}
		//转换闰月农历 需补充该年闰月的前一个月的时差
		if(isLeapMonth) {offset+=day;}
		//1900年农历正月一日的公历时间为1900年1月30日0时0分0秒(该时间也是本农历的最开始起始点)
		var stmap   =   Date.UTC(1900,1,30,0,0,0);
		var calObj  =   new Date((offset+d-31)*86400000+stmap);
		var cY      =   calObj.getUTCFullYear();
		var cM      =   calObj.getUTCMonth()+1;
		var cD      =   calObj.getUTCDate();

		return this.solar2lunar(cY,cM,cD);
	},
	
	defaultLunar : {
	  '1y1': '春节',
	  '1y15': '元宵节',
	  '2y2': '龙头节',
	  '5y5': '端午节',
	  '7y7': '七夕节',
	  '7y15': '中元节',
	  '8y15': '中秋节',
	  '9y9': '重阳节',
	  '10y1': '寒衣节',
	  '10y15': '下元节',
	  '12y8': '腊八节',
	  '12y23': '小年',
	},
	
	defaultGregorian : {
	  '1y1': '元旦',
	  '2y14': '情人节',
	  '3y8': '妇女节',
	  '3y12': '植树节',
	  '5y1': '劳动节',
	  '5y4': '青年节',
	  '6y1': '儿童节',
	  '7y1': '建党节',
	  '8y1': '建军节',
	  '9y10': '教师节',
	  '10y1': '国庆节',
	  '12y24': '平安夜',
	  '12y25': '圣诞节',
	},
	
	getLunarInfo : function(y, m, d) {
		// 匹配阳历节日
		var glDay  = m+'y'+d;
		if(this.defaultGregorian[glDay]){
			return this.defaultGregorian[glDay];
		}
		var nlObj = this.solar2lunar(y, m, d);
		// 阴历节日
		var nlDay = nlObj.lMonth+'y'+nlObj.lDay;
		if(this.defaultLunar[nlDay]){
			return this.defaultLunar[nlDay]
		}
		return nlObj.IDayCn;
	}
};
export default{
	name : "gui-schedule",
	data() {
		return {
			cYear      : 2020,
			cMonth     : 1,
			cDay       : 10,
			cMonthStr  : '01',
			weeks      : ['一', '二', '三', '四', '五', '六', '日'],
			days       : [],
			currentDayIn : '',
			hours      : ['00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23'],
			schedulesIn: [] 
		}
	},
	props:{
		// 当前默认日期
		currentDate   : {type:String, default:""},
		bgColor       : {type:String, default:"#F8F8F8"},
		activeBgColor : {type:String, default:"#008AFF"},
		isLunar       : {type:Boolean, default:true },
		startDate     : {type:String, default:'1950-01-01'},
		endDate       : {type:String, default:'2050-01-01'},
		schedules     : {type:Array, default:function () {return []}},
		pointColor    : {type:String, default:"#FF0036"}
	},
	created:function(){
		this.currentDayIn = this.currentDate;
		this.initTime();
		this.getSchedulesIn();
	},
	methods:{
		initTime : function(){
			if(this.currentDayIn == ''){
				var dateObj        = new Date();
				this.cYear         = Number(dateObj.getFullYear());
				this.cMonth        = Number(dateObj.getMonth() + 1);
				this.cMonthStr     = this.cMonth < 10 ? '0' + this.cMonth : this.cMonth;
				this.cDay          = dateObj.getDate();
				this.cDay          = this.cDay < 10 ? '0' + this.cDay : this.cDay;
				this.currentDayIn  = this.cYear + '-' + this.cMonthStr + '-' + this.cDay;
				this.changeMonth();
			}else{
				var dates          = this.currentDayIn.split(' ');
				if (!dates[1]) { dates[1] = '';}
				var dayArr         = dates[0].split('-');
				this.cYear         = Number(dayArr[0]);
				this.cMonth        = dayArr[1];
				this.cDay          = dayArr[2];
				var reg            = new RegExp('^0[0-9]+$');
				if(reg.test(this.cMonth)){this.cMonth = this.cMonth.substr(1,1);}
				this.cMonth        = Number(this.cMonth);
				this.cMonthStr     = this.cMonth < 10 ? '0'+this.cMonth : this.cMonth;
				this.currentDayIn  = dates[0];
				this.currentTimeIn = dates[1];
				this.changeMonth();
			}
		},
		changeMonth:function(){
			var daysList  = [];
			var days      = this.getDaysInOneMonth();
			var startWeek = this.getDay();
			var forSteps  = 0;
			for (var i = (0 - startWeek); i < days; i++){
				if(i >= 0){
					daysList[forSteps] = {date : i >= 9 ? i + 1 : '0' + (i+1), nl : ''};
					daysList[forSteps].nl = guiCalendar.getLunarInfo(this.cYear, this.cMonth, i + 1);
					daysList[forSteps].haveSe = this.haveSchedule(daysList[forSteps].date);
				}else{
					daysList[forSteps] = '';
				}
				forSteps++;
			}
			this.days    = daysList;
		},
		haveSchedule : function (day) {
			var cDay = this.cYear+'-'+this.cMonthStr+'-'+day;
			for(let i = 0; i < this.schedules.length; i++){
				if(this.schedules[i].datetime.indexOf(cDay) != -1){
					return true;
				}
			}
			return false;
		},
		getDaysInOneMonth : function (){
			var d = new Date(this.cYear, this.cMonth, 0);
			return d.getDate();
		},
		getDay : function (){
			var d = new Date(this.cYear, this.cMonth - 1, 0);
			return d.getDay();
		},
		selectDate : function(e){
			this.currentDayIn = e.detail.value;
			this.initTime();
			this.getSchedulesIn();
			this.$emit('selectDate', e.detail.value);
		},
		chooseDate: function (sedDate) {
			this.currentDayIn = sedDate;
			this.getSchedulesIn();
			this.$emit('chooseDate', sedDate);
		},
		getSchedulesIn : function (){
			var res = [];
			for(let i = 0; i < this.hours.length; i++){
				var ctime = this.currentDayIn + ' ' + this.hours[i] + ':00';
				res.push([]);
				for(let ii = 0; ii < this.schedules.length; ii++){
					if(this.schedules[ii].datetime == ctime){
						res[i].push(this.schedules[ii]);
					}
				}
			}
			this.schedulesIn = res;
		},
		scheduleTap : function (e) {
			var id = e.currentTarget.dataset.id;
			this.$emit('scheduleTap', id);
		},
		gotoToday : function(){
			var dateObj        = new Date();
			this.cYear         = Number(dateObj.getFullYear());
			this.cMonth        = Number(dateObj.getMonth() + 1);
			this.cMonthStr     = this.cMonth < 10 ? '0' + this.cMonth : this.cMonth;
			this.cDay          = dateObj.getDate();
			this.cDay          = this.cDay < 10 ? '0' + this.cDay : this.cDay;
			this.currentDayIn  = this.cYear + '-' + this.cMonthStr + '-' + this.cDay;
			this.changeMonth();
			this.getSchedulesIn();
			this.$emit('gotoToday', this.currentDayIn);
		}
	}
}
</script>
<style scoped>
.gui-schedule-wrap{width:690rpx;}
.gui-schedule-header-date{height:88rpx; line-height:88rpx; color:#2B2E3D; font-size:32rpx;}
.gui-schedule-7item{width:98rpx; margin-bottom:22rpx; position:relative;}
.gui-schedule-weeks{width:98rpx; height:88rpx; font-size:26rpx; line-height:88rpx; text-align:center;}
.gui-date-ditems{width:82rpx; height:82rpx; border-radius:80rpx;}
.gui-d-current-txt{color:#FFFFFF !important;}
.gui-date-day{height:32rpx; line-height:32rpx; text-align:center; font-size:28rpx;}
.gui-date-nl{height:24rpx; line-height:26rpx; color:#888888; font-size:20rpx; text-align:center;}
.gui-schedule-line{height:20rpx; border-color:#F8F8F8;}
.gui-schedule-time-list{margin-top:20rpx;}
.gui-schedule-timer{width:88rpx; font-size:22rpx; line-height:36rpx;}
.gui-schedule-body{width:200rpx; flex:1; border-color:#F8F8F8; margin-top:15rpx;}
.gui-schedules{padding:10rpx; line-height:30rpx; font-size:22rpx; margin-top:15rpx; border-radius:8rpx;}
.gui-schedule-time-list-wrap{padding:20rpx;}
.gui-schedule-today{line-height:50rpx; height:50rpx; font-size:22rpx; color:#828282; padding-left:20rpx; padding-right:20rpx; border-color:#F1F2F3;}
.gui-schedule-point{width:18rpx; height:18rpx; border-radius:10rpx; background-color:#FF0036; position:absolute; right:6rpx; top:6rpx;}
</style>