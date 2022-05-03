import axios from 'axios';

const http = axios.create({
    baseURL: 'http://localhost:8080',
});

http.interceptors.request.use(
    function (config) {
      config.headers["Content-Type"] = "application/json; charset=utf-8";
      config.headers["Authorization"] = localStorage.getItem('jwtToken');
      return config;
    },
    function (error) {
      console.log(error);
      return Promise.reject(error);
    }
);

http.interceptors.response.use(
    function (response) {
        console.log('response:',response);
        //console.log('response.config.url:',response.config.url);
      if(response.config.url === '/login') {
          localStorage.setItem('jwtToken',response.headers['authorization']);
          return response.status;
      }  
      return response.data;
     
    },
    function (error) {
        console.log(error);
    }
);

export default http;

