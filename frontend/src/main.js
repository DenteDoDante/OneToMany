import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // Importa o Vue Router

const app = createApp(App);
app.use(router); // Adiciona o Vue Router
app.mount('#app');
