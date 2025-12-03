import { useEffect, useState } from "react";

function fetchUniverse() {

    // holds universe data
    const [universeData, setUniverseData] = useState<any>(null);

    // Runs API call on random universe generator ands sets data
    useEffect(() => {
        fetch("http://localhost:8080/universe/random?saveToDatabase=true") // replace in prod
            .then((res) => res.json())
            .then(setUniverseData)
            .catch(console.error);
    }, []);

    console.log("Fetched Universe Data:", universeData);

    return universeData;
}

export default fetchUniverse;