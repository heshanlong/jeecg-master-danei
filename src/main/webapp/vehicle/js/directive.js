Vue.directive('drag', {
  bind (el) {
    const dialogHeader = el.querySelector('.el-dialog__header')
    const dialog = el.querySelector('.el-dialog')
    dialogHeader.style.cursor = 'move'
    const style = dialog.currentStyle || window.getComputedStyle(dialog, null)
    dialogHeader.onmousedown = e => {
      const initialX = e.clientX
      const initialY = e.clientY
      const styleLeft = parseInt(style.left) || 0
      const styleTop = parseInt(style.top) || 0
      el.onmousemove = e => {
        dialog.style.left = e.clientX - initialX + styleLeft + 'px'
        dialog.style.top = e.clientY - initialY + styleTop + 'px'
      }
      el.onmouseup = () => {
        el.onmousemove = null
        el.onmouseup = null
      }
    }
  }
})

let findInput = children => {
  let el
  for (let child of children) {
    if (child.tagName.toLowerCase() === 'input') el = child
    else if (child.children.length) {
      el = findInput(child.children)
      if (el) break
    }
  }
  return el
}

let autoFocus = el => {
  if (el.tagName.toLowerCase() === 'input') el.focus()
  else {
    let input = findInput(el.children)
    if (input) input.focus()
  }
}

Vue.directive('focus', {
  inserted (el) { autoFocus(el) },
  update (el) { autoFocus(el) }
})
