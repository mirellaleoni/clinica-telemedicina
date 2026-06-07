import { ButtonStyled } from "./ButtonStyled";

function Button({text, onClick, type, ...props}){
    return(
        <ButtonStyled onClick={onClick} type={type} {...props}>
            {text}
        </ButtonStyled>
    )
}

export default Button;