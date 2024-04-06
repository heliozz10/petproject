import styles from './SearchBar.module.css'

const search = (
    <div className={styles["rainbow"]}>
      <img src="../images/search (1).svg" alt="" />
    </div>
  );

  function SearchBar() {
    return search;
  }

  export default SearchBar;