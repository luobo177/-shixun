const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

// const { defineConfig } = require('@vue/cli-service');

// module.exports = defineConfig({
//   transpileDependencies: true,
//   devServer: {
//     proxy: {
//       '/api': {
//         target: 'http://localhost:8112', // 将代理目标指向后端服务器地址
//         changeOrigin: true, // 允许跨域
//         pathRewrite: {
//           '^/api': '' // 去掉请求中的 /api 前缀
//         }
//       }
//     }
//   }
// });

