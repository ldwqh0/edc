export function randomKey () {
  return new Date().getTime().toString() + Math.random().toString().substring(3)
}
