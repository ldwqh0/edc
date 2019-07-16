const translateUrl = function (url) {
  const [protocol, , host, ...pathArr] = url.split('/')

  let hostname, port
  if (host !== undefined && host !== null) {
    [hostname, port] = host.split(':')
  }
  const pathname = pathArr.join('/')

  return {
    protocol, hostname, host, port, pathname
  }
}

export default translateUrl
