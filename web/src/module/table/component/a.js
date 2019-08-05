const a = require('./c.json')
const r = a.map(item => ({ ...item, parent: item.parent.code })).map(item => {
  delete item.parents
  return item
})

console.log(JSON.stringify(r))
