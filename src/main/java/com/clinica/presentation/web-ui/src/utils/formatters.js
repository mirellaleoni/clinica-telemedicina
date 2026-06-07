export function getId(item) {
    return item?.id || item?.uuid || "";
}

export function shortId(id) {
    if (!id) return "-";
    return String(id).slice(0, 8);
}

export function formatDate(value) {
    if (!value) return "-";
    if (/^\d{4}-\d{2}-\d{2}$/.test(value)) {
        const [year, month, day] = value.split("-");
        return `${day}/${month}/${year}`;
    }
    return new Intl.DateTimeFormat("pt-BR", { timeZone: "America/Sao_Paulo" }).format(new Date(value));
}

export function formatDateTime(value) {
    if (!value) return "-";
    return new Intl.DateTimeFormat("pt-BR", {
        dateStyle: "short",
        timeStyle: "short",
        timeZone: "America/Sao_Paulo",
    }).format(new Date(value));
}

export function toApiDateTime(value) {
    if (!value) return "";
    return value.length === 16 ? `${value}:00` : value;
}

export function toInputDateTime(value) {
    if (!value) return "";
    return String(value).slice(0, 16);
}

export function readApiError(error, fallback = "Nao foi possivel concluir a operacao.") {
    const data = error?.response?.data;

    if (typeof data === "string") return data;
    if (data?.message) return data.message;

    return fallback;
}
