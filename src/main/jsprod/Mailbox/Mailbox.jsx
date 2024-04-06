import React, {useState} from 'react';
import styles from './Mailbox.module.css'
import 'animate.css';

function MailBox(props) {

    const [isToDisabled, setIsToDisabled] = useState(true);
    const [name, setName] = useState("Guest");
    const [surname, setSurname] = useState("");

    const regForm = document.getElementById('reg');
    const to = document.getElementById('to');
    const from = document.getElementById('from');
    const text = document.getElementById('text');

    const [display, setDisplay] = useState("block");
    const [filter, setFilter] = useState("blur(4px)");

    const regClick = () => {
        setDisplay("none");
        setFilter("none");
        setIsToDisabled(false);
    }

    function handleNameChanged(event) {
        setName(event.target.value);
    }

    function handleSurnameChanged(event) {
        setSurname(event.target.value);
    }

    return(
        <>
            <div className={styles.container}>
                <div className={styles["mail-wrapper"]} id='mail-wrapper'>
                    <div className={`${styles.reg} animate__animated animate__fadeIn`} id='reg-form' style={{display: display}}>
                        <div className={styles["title"]}>
                            Registration {name}
                        </div>
                        <form action="/register-process" method="POST">
                                <input type="text" id='get-name' name='firstName' placeholder='First name' onChange={handleNameChanged}/>
                                <input type="text" id='get-second-name' name='secondName' placeholder='Second name' onChange={handleSurnameChanged}/>
                                
                                <input type="text" id='username' name='username' placeholder='Username'/>
                                
                                <input type="text" id='password' name='password' placeholder='Password'/>
                        </form>
                        <button className={styles['submit-full-name']} onClick={regClick} > Save and Continue</button>
                    </div>

                    <div className={styles["mail-form"]}>
                        <form id='mail-form' action="" style={{filter: filter}}>
                            <div className="from">
                                <label htmlFor="from">From: </label>
                                <input type="text" id='from' name='from' value={`${name} ${surname}`} disabled={isToDisabled}/>
                            </div>

                            <div className="to">
                                <label htmlFor="to">To: </label>
                                <input type="text" id='to' name="to" disabled={isToDisabled}/>
                            </div>

                            <div className="text" id='text-div'>
                                <label htmlFor="text"></label>
                                <textarea id='text' className={styles['text']} name='text' disabled={isToDisabled}/>
                            </div>

                            <button className={styles["send"]} disabled={isToDisabled}>Send</button>
                        </form>
                        
                    </div>
                </div>
            </div>
        </>
    )
}

export default MailBox