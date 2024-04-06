import styles from './Card.module.css';

function Card(props) {

    
    return (
        <div className={styles.card}>
            <img src="https://via.placeholder.com/100" alt="" />
            <h2 className={styles['card-name']}>{props.name}</h2>
            <p className={styles['card-number']}>{props.number}</p>
            {/* <p>Is Student?: {props.isStudent ? "Yes" : "No"}</p> */}
        </div>
    );
}

export default Card;



