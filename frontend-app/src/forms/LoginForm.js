import React, {Component} from "react";
import logo from "../media/images/dossier-logo.jpg";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

class LoginForm extends Component {

    col_md_12 = {
        display: "flex",
        textAlign: "left",
        marginBottom: "20px",
        marginTop: "20px",
        paddingRight: "0px",
        paddingLeft: "0px"
    };

    col_md_12_extended = {
        paddingRight: "0px",
        paddingLeft: "0px"
    };

    buttonStyle = {
        marginBottom: "20px"
    };

    render() {
        const {
            click,
            keyPress,
            email,
            password,
            changePassword,
            changeEmail
        } = this.props;

        return (
            <div>
                <div className="col-md-12" style={this.col_md_12}>
                    <div className="col-md-12" style={this.col_md_12_extended}>
                        <img className="rounded" width="150px" src={logo} alt="dossier"/>
                        <div className="page-header">
                            <h4># Sample OAuth2 with JWT</h4>
                            <small>Enter user credentials</small>
                        </div>
                    </div>
                </div>

                <Form>
                    <Form.Group controlId="formBasicEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control onChange={changeEmail} value={email} type="email" placeholder="Enter email"/>
                    </Form.Group>

                    <Form.Group controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control onChange={changePassword} value={password} type="password"
                                      placeholder="Password"/>
                    </Form.Group>
                    <hr/>
                    <Button style={this.buttonStyle} onClick={click} onKeyPress={keyPress} variant="primary"
                            type="button">
                        Log In
                    </Button>
                </Form>
            </div>
        );
    }
}

export default LoginForm;
