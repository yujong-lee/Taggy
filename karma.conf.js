module.exports = function (config) {
  config.set({
    autoWatch: true,
    singleRun: false,

    browsers: ['jsdom'],
    basePath: 'target',
    files: ['ci.js'],
    frameworks: ['cljs-test'],
    plugins: [
      'karma-cljs-test',
      'karma-jsdom-launcher', 
    ],
    
    colors: true,
    logLevel: config.LOG_INFO,
    client: {
      args: ["shadow.test.karma.init"],
    },
  })
}
