import styles from './Header.module.css';
import 'animate.css';
import SearchBar from './searchBar/SearchBar.jsx'

function Header() {

    return (
        <header className={styles.header}>
            <div className="container">
                <div className="wrapper">
                    <div className={styles["header-wrapper"]}>
                        <div className={`${styles.logo}`}>
                            <img src='../images/bird_2.jpg' alt="logo.jpg"/>
                            <div className={styles['logo-text']}>Nifferi</div>
                        </div>
                        <SearchBar />
                        <nav>
                            <ul>
                                <li><a href="">About</a></li> 
                                <li><a href="">Startups</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </header>
    );
}


export default Header;
