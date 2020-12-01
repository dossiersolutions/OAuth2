import React, {Component} from "react";
import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";
import Form from "react-bootstrap/Form";
import FormControl from "react-bootstrap/FormControl";
import InputGroup from "react-bootstrap/InputGroup";
import Label from "react-bootstrap/FormLabel";
import Button from "react-bootstrap/Button";
import {bindActionCreators} from "redux";
import {updateResources} from "../redux/actions/populateActions";
import {popUp} from "../utils/Util";
import {doPostGuest} from "../redux/actions/serverActions"
import {
    POPULATE_KEY_ADD_GUEST,
    POPULATE_KEY_LOG_IN,
    POPULATE_KEY_USER_DETAILS,
    TYPE_ADD_GUEST,
    TYPE_LOG_IN
} from "../redux/actions/constants";
import {connect} from "react-redux";
import {decrypt} from "../redux/crypting/crypt";

class AddGuest extends Component {
    constructor(props) {
        super(props);

        this.state = {
            firstName: "",
            lastName: "",
            description: "",
            invited: "false",
            confirmed: "unknown"
        }
    }

    span = {
        marginLeft: "5px",
        marginRight: "5px"
    };

    wrapper = {
        display: "inline"
    };

    logout = {
        float: "right",
        textDecoration: "underline",
        fontWeight: "bold",
        cursor: "pointer"
    };


    componentDidMount() {
        const authToken = decrypt(window.sessionStorage.getItem("token"));

        if (!authToken) {
            this.logoutUser();
        }
    }

    componentWillReceiveProps(nextProps) {
        const {
            guestAdded
        } = this.props;

        const {
            guestAdded: nextGuestAdded
        } = nextProps;

        if (!guestAdded && nextGuestAdded && nextGuestAdded === "success") {
            this.guestPage();
        }
    }

    componentWillUnmount() {
        const {
            updateResources
        } = this.props;

        updateResources(null, POPULATE_KEY_ADD_GUEST, TYPE_ADD_GUEST);
    }

    title = {
        fontSize: "22px"
    };

    panelStyle = {
        marginTop: "20px",
        marginBottom: "20px",
    };

    componentStyle = {
        marginTop: "10px",
    };

    commonMargin = {
        marginTop: "10px",
        display: "block"
    };

    dropDownItem = {
        display: "block",
        marginLeft: "10px",
        fontSize: "14px",
        color: "#303030"
    };

    generateFirstName() {
        return (
            <div style={this.componentStyle}>
                <InputGroup size="sm" className="mb-3">
                    <InputGroup.Prepend>
                        <Label>Name</Label>
                    </InputGroup.Prepend>
                    <FormControl value={this.state.firstName} onChange={(event) => this.activitySelectFirstName(event)}
                                 aria-label="Small" aria-describedby="inputGroup-sizing-sm"/>
                </InputGroup>
            </div>
        )
    }

    activitySelectFirstName(event) {
        this.setState({firstName: event.target.value});
    }

    generateLastName() {
        return (
            <div style={this.componentStyle}>
                <InputGroup size="sm" className="mb-3">
                    <InputGroup.Prepend>
                        <Label>Surname</Label>
                    </InputGroup.Prepend>
                    <FormControl value={this.state.lastName} onChange={(event) => this.activitySelectLastName(event)}
                                 aria-label="Small" aria-describedby="inputGroup-sizing-sm"/>
                </InputGroup>
            </div>
        )
    }

    activitySelectLastName(event) {
        this.setState({lastName: event.target.value});
    }

    generateDescription() {
        return (
            <div style={this.componentStyle}>
                <Form.Group controlId="exampleForm.ControlTextarea1">
                    <Form.Label>Description</Form.Label>
                    <Form.Control value={this.state.description}
                                  onChange={(event) => this.activitySelectDescription(event)} as="textarea" rows="3"/>
                </Form.Group>
            </div>
        )
    }

    activitySelectDescription(event) {
        this.setState({description: event.target.value});
    }

    generateInvited() {
        const {
            invited
        } = this.state;

        let selectedInvited = null;

        if (invited === "" || !invited) {
            selectedInvited = "Pozvan..."
        } else {
            selectedInvited = invited;
        }

        return (
            <div style={this.commonMargin}>
                <Label>Invited</Label>
                <DropdownButton variant={"secondary"} id="dropdown-basic-button" title={selectedInvited}>
                    <Dropdown.Item style={this.dropDownItem}
                                   onClick={() => this.activitySelectInvited("false")}>false</Dropdown.Item>
                    <Dropdown.Item style={this.dropDownItem}
                                   onClick={() => this.activitySelectInvited("true")}>true</Dropdown.Item>
                </DropdownButton>
            </div>
        );
    }

    activitySelectInvited(selectInvited) {
        this.setState({invited: selectInvited});
    }

    generateConfirmed() {
        const {
            confirmed
        } = this.state;

        let selectedConfirmed = null;

        if (confirmed === "" || !confirmed) {
            selectedConfirmed = "Confirmed..."
        } else {
            selectedConfirmed = confirmed;
        }

        return (
            <div style={this.commonMargin}>
                <Label>Confirmed</Label>
                <DropdownButton variant={"secondary"} id="dropdown-basic-button" title={selectedConfirmed}>
                    <Dropdown.Item style={this.dropDownItem}
                                   onClick={() => this.activitySelectConfirmed("true")}>true</Dropdown.Item>
                    <Dropdown.Item style={this.dropDownItem}
                                   onClick={() => this.activitySelectConfirmed("false")}>false</Dropdown.Item>
                    <Dropdown.Item style={this.dropDownItem}
                                   onClick={() => this.activitySelectConfirmed("unknown")}>unknown</Dropdown.Item>
                </DropdownButton>
            </div>
        );
    }

    activitySelectConfirmed(selectConfirmed) {
        this.setState({confirmed: selectConfirmed});
    }

    guestPage() {
        const {
            history
        } = this.props;

        history.push("/guests");
    }

    saveGuest() {
        const {
            firstName,
            lastName,
            description,
            invited,
            confirmed,
        } = this.state;

        const {
            doPostGuest
        } = this.props;

      const userId = decrypt(window.sessionStorage.getItem("userId"));

      if (!firstName || firstName === "") {
            popUp("Error", "Wrong name", null, true);
        } else if (!lastName || lastName === "") {
            popUp("Error", "Wrong surname", null, true);
        } else if (!invited || invited === "") {
            popUp("Error", "Wrong entry for invited", null, true);
        } else if (!confirmed || confirmed === "") {
            popUp("Error", "Wrong entry for confirmed", null, true);
        } else {
            doPostGuest(
                firstName, lastName, description,
                invited, confirmed, userId, POPULATE_KEY_ADD_GUEST, TYPE_ADD_GUEST);
        }
    }

    logoutUser() {
        const {
            history,
            updateResources
        } = this.props;

        const result = {
            data: {
                "access_token": null
            }
        };
        updateResources(result, POPULATE_KEY_LOG_IN, TYPE_LOG_IN);
        history.push("/");
    }


    generateButtonPanel() {
        const userEmail = "(" + decrypt(window.sessionStorage.getItem("userEmail")) + ")";
        return (
            <div style={this.wrapper}>
                <div style={this.logout} onClick={() => this.logoutUser()}>{userEmail + " Logout"}</div>
                <div style={this.buttonPanel}>
                    <Button onClick={() => this.guestPage()}
                            style={this.buttonStyle}
                            variant="primary"
                            type="button">Guests</Button>
                    <span style={this.span}/>
                </div>
            </div>
        )
    }

    render() {
        const titleJsx = (
            <div style={this.title}>Add guest</div>
        );

        const saveJsx = (
            <div>
                <hr/>
                <Button onClick={() => this.saveGuest()}>Save</Button>
            </div>
        );


        return (
            <div style={this.panelStyle}>
                {this.generateButtonPanel()}
                <hr/>
                {titleJsx}
                {this.generateFirstName()}
                {this.generateLastName()}
                {this.generateDescription()}
                {this.generateInvited()}
                {this.generateConfirmed()}
                {saveJsx}
            </div>
        )
    }
}

const mapStateToProps = state => {
    const guestAdded = state.datareducer.get(POPULATE_KEY_ADD_GUEST);
    const userDetails = state.datareducer.get(POPULATE_KEY_USER_DETAILS);

  return {
        guestAdded,
        userDetails
    };
};

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        doPostGuest,
        updateResources
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(AddGuest);