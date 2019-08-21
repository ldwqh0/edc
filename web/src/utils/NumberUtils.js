function toTenThousand (number, digits = 2) {
  return Number.parseFloat((number / 10000).toFixed(digits))
}

function toMillion (number, digits = 2) {
  return Number.parseFloat((number / 100000000).toFixed(digits))
}

export default {
  toMillion,
  toTenThousand
}
export {
  toTenThousand,
  toMillion
}
