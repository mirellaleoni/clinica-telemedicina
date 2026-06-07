function Input({ name, type, placeholder, register, ...props }) {
    return (
        <input 
            type={type}
            placeholder={placeholder}
            {...(register && register(name))}
            {...props}
        />
    );
}

export default Input;