import { useEffect } from "react";
import { useParams } from "react-router-dom";

function Redirect() {
    const { code } = useParams();

    useEffect(() => {
        window.location.replace(`/api/url/${code}`);
    }, [code]);

    return <p style={{ textAlign: "center" }}>Redirecting...</p>;
}

export default Redirect;