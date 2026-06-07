import { ButtonStyled } from "./ButtonStyled";

function Button({text, onClick, ...props}){
    return(
        <ButtonStyled onClick={onClick}>
            {text}
        </ButtonStyled>
    )
}

export default Button;