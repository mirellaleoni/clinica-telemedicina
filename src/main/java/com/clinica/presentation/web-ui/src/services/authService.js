import { api } from "./api";

export async function loginService(dadosLogin) {
    const response = await api.post("/auth/login", dadosLogin);
    return response.data;
}