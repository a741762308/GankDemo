const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}
const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}
const dateFormat = date => {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()
  var format = [year, month, day].map(formatNumber).join('/')
  return format
}
const dateTimeStatus = date => {
  var hour = date.getHours()
  if (hour >= 5 && hour < 12) {
    return 0;
  } else if (hour >= 12 && hour < 17) {
    return 1;
  } else {
    return 2;
  }
}
module.exports = {
  formatTime: formatTime,
  dateFormat: dateFormat,
  dateTimeStatus: dateTimeStatus
}
