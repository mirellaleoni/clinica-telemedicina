import * as z from "zod";

export const loginSchema = z.object({
  email: z.string()
    .min(1, "O e-mail e obrigatorio")
    .email("Formato de e-mail invalido"),
  senha: z.string()
    .min(6, "A senha deve ter no minimo 6 caracteres"),
});
