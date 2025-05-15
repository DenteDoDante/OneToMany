<template>
    <div id="login">
        <h2>Login</h2>
        <form @submit.prevent="login">


            <label for="email">Email:</label>
            <input v-model="email" type="email" id="email" name="email" required /><br /><br />

            <label for="password">Senha:</label>
            <input v-model="password" type="password" id="password" name="password" required /><br /><br />

            <button type="submit">Logar</button>
        </form>
        <p>Não tem uma conta? <router-link to="/register">Cadastre-se</router-link></p>
    </div>
</template>

<script>
export default {
    name: "LoginView",
    data() {
        return {
            email: "",
            password: "",
        };
    },

    methods: {
        async login() {
            try {
                const response = await fetch("http://localhost:8888/api/auth/login", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        email: this.email,
                        password: this.password,
                    }),
                });
                const result = await response.json();
                if (response.ok) {
                    alert("Login feito com sucesso");
                } else {
                    alert(result.message || "Erro ao fazer login");
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