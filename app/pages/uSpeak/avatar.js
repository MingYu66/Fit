// utils/avatar.js

// 生成名称缩写（例如：张三 → ZS，John Doe → JD）
export const getNameAbbr = (name) => {
  if (!name) return '';
  // 处理中文和英文
  const letters = name.match(/[\u4e00-\u9fa5]|[a-zA-Z]/g) || [];
  let abbr = '';
  if (letters.length >= 2) {
    abbr = letters[0] + letters[letters.length - 1];
  } else {
    abbr = letters[0] || 'U';
  }
  return abbr.toUpperCase().substring(0, 2);
};

// 生成固定背景色（基于名称哈希）
export const getBackgroundColor = (name) => {
  const colors = [
    '#FFB6C1', '#87CEEB', '#98FB98', '#FFD700',
    '#FFA07A', '#9370DB', '#00CED1', '#FF69B4'
  ];
  let hash = 0;
  for (let i = 0; i < (name || '').length; i++) {
    hash = name.charCodeAt(i) + ((hash << 5) - hash);
  }
  return colors[Math.abs(hash) % colors.length];
};

// 生成头像图片
export const generateAvatar = async (name, size = 100) => {
  const abbr = getNameAbbr(name);
  const bgColor = getBackgroundColor(name);
  
  // 创建 Canvas 上下文
  const canvasId = 'avatar-canvas';
  const ctx = uni.createCanvasContext(canvasId, this);
  
  // 绘制圆形背景
  ctx.beginPath();
  ctx.arc(size/2, size/2, size/2, 0, 2 * Math.PI);
  ctx.fillStyle = bgColor;
  ctx.fill();
  
  // 绘制文字
  ctx.font = `normal bold ${size * 0.4}px sans-serif`;
  ctx.fillStyle = '#FFFFFF';
  ctx.textAlign = 'center';
  ctx.textBaseline = 'middle';
  ctx.fillText(abbr, size/2, size/2);
  
  // 绘制完成
  await new Promise(resolve => ctx.draw(false, resolve));
  
  // 生成临时图片路径
  const res = await uni.canvasToTempFilePath({
    canvasId,
    quality: 1,
  });
  return res.tempFilePath;
};