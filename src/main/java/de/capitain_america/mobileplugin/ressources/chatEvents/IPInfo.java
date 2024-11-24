package de.capitain_america.mobileplugin.ressources.chatEvents;

import com.google.gson.Gson;
import de.capitain_america.mobileplugin.ressources.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.text.MessageFormat;

public class IPInfo {

    private final Logger _logger = new Logger();

    public IPInfo(Player player, String playerName) {
        try {
            if (Bukkit.getPlayer(playerName) == null) throw new Exception();
            GetInfo(player, Bukkit.getPlayer(playerName));
        }
        catch (Exception ex)
        {
            _logger.log(player, MessageFormat.format("Player: {0} could not be found!", playerName));
        }
    }

    private void GetInfo(Player player, Player target) throws IOException {
        try {
            InetSocketAddress socketInfo = target.getAddress();
            StringBuilder infos = new StringBuilder();
            assert socketInfo != null;
            IPInfoObject ipInfo = GetInfosFromApi(player, socketInfo.getHostString());

            infos.append(MessageFormat.format("Player: {0} Status: {1}:\n", target.getDisplayName(), ipInfo.status));
            infos.append(MessageFormat.format("Address: {0} : {1} Ping: {2}\n", ipInfo.query, target.getAddress().getPort(), target.getPing()));
            infos.append(MessageFormat.format("Provider: {0}\n", ipInfo.isp));
            infos.append(MessageFormat.format("Timezone: {0}\n", ipInfo.timezone));
            infos.append(MessageFormat.format("Country {0} \"{1}\"\n", ipInfo.country, ipInfo.countryCode));
            infos.append(MessageFormat.format("Region: {0} \"{1}\"\n", ipInfo.regionName, ipInfo.region));
            infos.append(MessageFormat.format("City: {0} \"{1}\"\n", ipInfo.city, ipInfo.zip));
            infos.append(MessageFormat.format("Coordinates: {0} : {1}\n", ipInfo.lat, ipInfo.lon));
            _logger.log(player, infos.toString());
        }
        catch (Exception e)
        {
            _logger.log(player, "Ip info failed!");
        }
    }

    private IPInfoObject GetInfosFromApi(Player player, String address) throws IOException {
        URL url = new URL("http://ip-api.com/json/" + address);
        HttpURLConnection apiConnection = (HttpURLConnection) url.openConnection();
        apiConnection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));

        String info;
        StringBuilder infos = new StringBuilder();

        while ((info = reader.readLine()) != null) {
            infos.append(info);
        }
        apiConnection.disconnect();
        reader.close();
        return new Gson().fromJson(infos.toString(), IPInfoObject.class);
    }
}
