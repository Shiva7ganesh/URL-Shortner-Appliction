import { toast } from "react-toastify";

function fdd(setLoading, setData) {
    setLoading(true);
    let token = localStorage.getItem("urltoken");
    if (!token) {
        setLoading(false);
        return;
    }
    fetch("/api/url/dashboard", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        body: JSON.stringify({ token: token }),
    }).then(res => res.json()).then(data => {
        if (data.error) {
            toast.error(data.message)
            localStorage.removeItem("urltoken")
        } else {

            const lis = data.urls.map(item => ({
                code: item.shortUrl,
                long: item.orgUrl,
                count: item.count
            }));

            if (lis.length === 0) {
                setData(null)
            } else {
                lis.sort((a, b) => b.count - a.count);
                setData(lis);
            }
        }
        setLoading(false);
    })
}

export default fdd;