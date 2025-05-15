<template>
  <div id="app">
    <h2>Register</h2>
    <form @submit.prevent="register">

      <label for="name">Name:</label>
      <input v-model="name" type="text" id="name" name="name" /><br /><br />

      <label for="email">Email:</label>
      <input v-model="email" type="email" id="email" name="email" required /><br /><br />

      <label for="password">Senha:</label>
      <input v-model="password" type="password" id="password" name="password" required /><br /><br />

      <button type="submit">Registrar</button>
    </form>
    <p>Já tem uma conta? <router-link to="/login">Faça login</router-link></p>
  </div>
</template>

<script>
export default {
  name: "RegisterView",
  data() {
    return {
      name: "",
      email: "",
      password: "",
    };
  },

  methods: {
    async register() {
      try {
        const response = await fetch("http://localhost:8888/api/auth/register", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            name: this.name,
            email: this.email,
            password: this.password,
          }),
        });
        const result = await response.json();
        if (response.ok) {
          alert("Registro bem-sucedido");
        } else {
          alert(result.message || "Erro ao registrar");
        }
      } catch (error) {
        console.error("Erro de conexão", error);
        alert("Erro de conexão");

      }

    }
  }

}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
