import "./footer.css"

function Footer() {
    return (
        <div className="footer">
            <p>This website is Developed by <a href="/">Shiva Ganesh</a> &copy; {new Date().getFullYear()} </p>
        </div>
    )
}

export default Footer;