const webpack = require('webpack')

module.exports = {
  publicPath: process.env.CONTEXT_PATH,
  assetsDir: 'static',
  devServer: {
    port: 80,
    proxy: {
      // 认证的地址，单独填写
      '/edc/d/': {
        changeOrigin: true,
        target: 'http://127.0.0.1:8080/',
        pathRewrite: { [`${process.env.CONTEXT_PATH}d/`]: '' }
      }
    },
  },
  configureWebpack: {
    entry: ['core-js/stable', 'regenerator-runtime/runtime', './src'],
    plugins: [
      new webpack.DefinePlugin({
        // 定义一个全局的上下文变量，目的是为了发布时能够通过目录区分不同的项目，而不用单独占用一个端口
        'CONTEXT_PATH': JSON.stringify(process.env.CONTEXT_PATH)
      })
    ]
  }
}
