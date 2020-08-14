package me.AlaDyn172.UReport;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
  extends JavaPlugin
  implements Listener
{
  public final Logger logger = Logger.getLogger("Minecraft");
  PluginDescriptionFile pdfFile = getDescription();
  FileConfiguration config;
  File cfile;
  protected Logger log;
  public static boolean UpdateCheck;
  public String ConsoleCMD = "Commands work only in console!";
  public String UReport1 = getConfig().getString("UReport1");
  public String UReport2 = getConfig().getString("UReport2");
  public String UReport3 = getConfig().getString("UReport3");
  public String UReport4 = getConfig().getString("UReport4");
  public String UReport5 = getConfig().getString("UReport5");
  public String NR1 = getConfig().getString("NR1");
  public String NR2 = getConfig().getString("NR2");
  public String NR3 = getConfig().getString("NR3");
  public String NR4 = getConfig().getString("NR4");
  public String NR5 = getConfig().getString("NR5");
  public String Reason1 = getConfig().getString("Reason1");
  public String Reason2 = getConfig().getString("Reason2");
  public String Reason3 = getConfig().getString("Reason3");
  public String Reason4 = getConfig().getString("Reason4");
  public String Reason5 = getConfig().getString("Reason5");
  public String reportedBy = getConfig().getString("reportedBy");
  public String forReported = getConfig().getString("forReported");
  public String messageToOP = getConfig().getString("messageToOP");
  public String ConfigReset = getConfig().getString("ConfigReset");
  public String ConfigPlayerReset = getConfig().getString("ConfigPlayerReset");
  public String DutyON = getConfig().getString("DutyON");
  public String DutyOFF = getConfig().getString("DutyOFF");
  public String RSuccess = getConfig().getString("RSuccess");
  public String NoAdminsDuty = getConfig().getString("NoAdminsDuty");
  public String PlayerNotFound = getConfig().getString("PlayerNotFound");
  public String NoPermission = getConfig().getString("NoPermission");
  public String AlreadyONDuty = getConfig().getString("AlreadyONDuty");
  public String AlreadyOFFDuty = getConfig().getString("AlreadyOFFDuty");
  public String NowONDuty = getConfig().getString("NowONDuty");
  public String NowOFFDuty = getConfig().getString("NowOFFDuty");
  public boolean griefTp = getConfig().getBoolean("GriefTeleport");
  static int joinLeft = 0;
  
  ConsoleCommandSender console = getServer().getConsoleSender();
  
  @Override
  public void onEnable()
  {
    if (!getConfig().getString("Version").equals("1"))
    {
      this.logger.severe("[UReport] Plugin disabled due to modify the version from config.yml.");
      this.logger.severe("[UReport] Put Version string to 1, if you want that plugin to work!");
      Bukkit.getPluginManager().disablePlugin(this);
    }
    this.config = getConfig();
    this.config.options().copyDefaults(true);
    saveConfig();
    Bukkit.getServer().getPluginManager().registerEvents(this, this);
    this.cfile = new File(getDataFolder(), "config.yml");
    saveConfig();
    new Metrics(this);
    
    console.sendMessage(ChatColor.GREEN + "[UReport] Plugin developed by Echo. You're using v5.2!");
  }
  
  @Override
  public void onDisable()
  {
    try
    {
      getConfig().save("config.yml");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    saveConfig();
  }
  
  @EventHandler
  public void onServerEnter(PlayerJoinEvent e)
  {
    joinLeft += 1;
    if (joinLeft <= 1) {
      return;
    }
    joinLeft = 0;
    
    Player p = e.getPlayer();
    
    getConfig().set(p.getName(), Boolean.valueOf(false));
    saveConfig();
    reloadConfig();
  }
  
  @SuppressWarnings("deprecation")
public void openGui(Player player, String nameGui, String Reason)
  {
    if (nameGui == "player")
    {
      Inventory inv = Bukkit.createInventory(player, 9, ChatColor.WHITE + "Select reason:");
      
      ItemStack item = new ItemStack(Material.COMPASS);
      ItemMeta itemM = item.getItemMeta();
      itemM.setDisplayName(ChatColor.WHITE + "Close");
      item.setItemMeta(itemM);
      inv.setItem(inv.getSize() - 1, item);
            
      ItemStack item01 = new ItemStack(Material.WHITE_WOOL);
      ItemMeta itemM01 = item01.getItemMeta();
      itemM01.setDisplayName(ChatColor.GREEN + "Abusing");
      item01.setItemMeta(itemM01);
      inv.setItem(0, item01);
      
      ItemStack item02 = new ItemStack(Material.WHITE_WOOL);
      ItemMeta itemM02 = item02.getItemMeta();
      itemM02.setDisplayName(ChatColor.RED + "Spamming");
      item02.setItemMeta(itemM02);
      inv.setItem(1, item02);
      
      ItemStack item03 = new ItemStack(Material.WHITE_WOOL);
      ItemMeta itemM03 = item03.getItemMeta();
      itemM03.setDisplayName(ChatColor.BLUE + "Griefing");
      item03.setItemMeta(itemM03);
      inv.setItem(2, item03);
      
      ItemStack item04 = new ItemStack(Material.WHITE_WOOL);
      ItemMeta itemM04 = item04.getItemMeta();
      itemM04.setDisplayName(ChatColor.GRAY + "Cheating");
      item04.setItemMeta(itemM04);
      inv.setItem(3, item04);
      
      ItemStack item05 = new ItemStack(Material.WHITE_WOOL);
      ItemMeta itemM05 = item05.getItemMeta();
      itemM05.setDisplayName(ChatColor.YELLOW + "Other");
      item05.setItemMeta(itemM05);
      inv.setItem(4, item05);
      
      player.openInventory(inv);
    }
    ItemStack item01;
    if (nameGui == "admin")
    {
      Inventory inv = Bukkit.createInventory(player, 9, ChatColor.WHITE + "Select:");
      
      ItemStack item = new ItemStack(Material.COMPASS);
      ItemMeta itemM = item.getItemMeta();
      itemM.setDisplayName(ChatColor.WHITE + "Close");
      item.setItemMeta(itemM);
      inv.setItem(inv.getSize() - 1, item);
      
      item01 = new ItemStack(Material.WHITE_WOOL);
      ItemMeta itemM01 = item01.getItemMeta();
      itemM01.setDisplayName(ChatColor.GREEN + "Bug");
      item01.setItemMeta(itemM01);
      inv.setItem(0, item01);
      
      ItemStack item02 = new ItemStack(Material.WHITE_WOOL);
      ItemMeta itemM02 = item02.getItemMeta();
      itemM02.setDisplayName(ChatColor.RED + "Problem");
      item02.setItemMeta(itemM02);
      inv.setItem(1, item02);
      
      player.openInventory(inv);
    }
    ItemStack item;
    if (nameGui == "Abusing")
    {
      Inventory inv = Bukkit.createInventory(player, 54, ChatColor.GOLD + "Abusing" + ChatColor.WHITE + " - menu");
      List<String> players = new ArrayList<String>();
      for (Player p : Bukkit.getOnlinePlayers()) {
        players.add(p.getName());
      }
      for (int x = 0; x < players.size(); x++)
      {
        item = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
        SkullMeta skull = (SkullMeta)item.getItemMeta();
        skull.setDisplayName(ChatColor.RED + (String)players.get(x));
        skull.setOwner((String)players.get(x));
        skull.setLore(Arrays.asList(new String[] { "", ChatColor.GRAY + "Click here to report" }));
        item.setItemMeta(skull);
        inv.addItem(new ItemStack[] { item });
      }
      player.openInventory(inv);
    }
    if (nameGui == "Spamming")
    {
      Inventory inv = Bukkit.createInventory(player, 54, ChatColor.GOLD + "Spamming" + ChatColor.WHITE + " - menu");
      List<String> players = new ArrayList<String>();
      for (Player p : Bukkit.getOnlinePlayers()) {
        players.add(p.getName());
      }
      for (int x = 0; x < players.size(); x++)
      {
        item = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
        SkullMeta skull = (SkullMeta)item.getItemMeta();
        skull.setDisplayName(ChatColor.RED + (String)players.get(x));
        skull.setOwner((String)players.get(x));
        skull.setLore(Arrays.asList(new String[] { "", ChatColor.GRAY + "Click here to report" }));
        item.setItemMeta(skull);
        inv.addItem(new ItemStack[] { item });
      }
      player.openInventory(inv);
    }
    if (nameGui == "Cheating")
    {
      Inventory inv = Bukkit.createInventory(player, 54, ChatColor.GOLD + "Cheating" + ChatColor.WHITE + " - menu");
      List<String> players = new ArrayList<String>();
      for (Player p : Bukkit.getOnlinePlayers()) {
        players.add(p.getName());
      }
      for (int x = 0; x < players.size(); x++)
      {
        item = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
        SkullMeta skull = (SkullMeta)item.getItemMeta();
        skull.setDisplayName(ChatColor.RED + (String)players.get(x));
        skull.setOwner((String)players.get(x));
        skull.setLore(Arrays.asList(new String[] { "", ChatColor.GRAY + "Click here to report" }));
        item.setItemMeta(skull);
        inv.addItem(new ItemStack[] { item });
      }
      player.openInventory(inv);
    }
    if (nameGui == "Griefing")
    {
      Inventory inv = Bukkit.createInventory(player, 54, ChatColor.GOLD + "Griefing" + ChatColor.WHITE + " - menu");
      List<String> players = new ArrayList<String>();
      for (Player p : Bukkit.getOnlinePlayers()) {
        players.add(p.getName());
      }
      for (int x = 0; x < players.size(); x++)
      {
        item = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
        SkullMeta skull = (SkullMeta)item.getItemMeta();
        skull.setDisplayName(ChatColor.RED + (String)players.get(x));
        skull.setOwner((String)players.get(x));
        skull.setLore(Arrays.asList(new String[] { "", ChatColor.GRAY + "Click here to report" }));
        item.setItemMeta(skull);
        inv.addItem(new ItemStack[] { item });
      }
      player.openInventory(inv);
    }
    if (nameGui == "Other")
    {
      Inventory inv = Bukkit.createInventory(player, 54, ChatColor.GOLD + "Other" + ChatColor.WHITE + " - menu");
      List<String> players = new ArrayList<String>();
      for (Player p : Bukkit.getOnlinePlayers()) {
        players.add(p.getName());
      }
      for (int x = 0; x < players.size(); x++)
      {
        item = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
        SkullMeta skull = (SkullMeta)item.getItemMeta();
        skull.setDisplayName(ChatColor.RED + (String)players.get(x));
        skull.setOwner((String)players.get(x));
        skull.setLore(Arrays.asList(new String[] { "", ChatColor.GRAY + "Click here to report" }));
        item.setItemMeta(skull);
        inv.addItem(new ItemStack[] { item });
      }
      player.openInventory(inv);
    }
  }
  
  @EventHandler
  public void onClickItem(InventoryClickEvent event)
  {
	  String invTitle = event.getView().getTitle();
	  
	  if(event.getInventory() != null) {
		  
		  this.UReport1 = this.UReport1.replaceAll("&", "§");
		    this.UReport2 = this.UReport2.replaceAll("&", "§");
		    this.UReport3 = this.UReport3.replaceAll("&", "§");
		    this.NR1 = this.NR1.replaceAll("&", "§");
		    this.NR2 = this.NR2.replaceAll("&", "§");
		    this.NR3 = this.NR3.replaceAll("&", "§");
		    this.NR4 = this.NR4.replaceAll("&", "§");
		    this.NR5 = this.NR5.replaceAll("&", "§");
		    this.reportedBy = this.reportedBy.replaceAll("&", "§");
		    this.messageToOP = this.messageToOP.replaceAll("&", "§");
		    
		    this.ConfigReset = this.ConfigReset.replaceAll("&", "§");
		    this.ConfigPlayerReset = this.ConfigPlayerReset.replaceAll("&", "§");
		    
		    this.RSuccess = this.RSuccess.replaceAll("&", "§");
		    this.NoAdminsDuty = this.NoAdminsDuty.replaceAll("&", "§");
		    this.PlayerNotFound = this.PlayerNotFound.replaceAll("&", "§");
		    this.NoPermission = this.NoPermission.replaceAll("&", "§");
		    this.AlreadyONDuty = this.AlreadyONDuty.replaceAll("&", "§");
		    this.AlreadyOFFDuty = this.AlreadyOFFDuty.replaceAll("&", "§");
		    this.NowONDuty = this.NowONDuty.replaceAll("&", "§");
		    this.NowOFFDuty = this.NowOFFDuty.replaceAll("&", "§");
		    		    
		    ItemStack item = event.getCurrentItem();
		    Player player = (Player) event.getWhoClicked();
		    if(item != null && item.hasItemMeta()) {
		    	String player2 = item.getItemMeta().getDisplayName();
			    if(item.hasItemMeta() && invTitle != null)
			    {
			    	if ((event.getWhoClicked() instanceof Player))
			        {
			          if ((invTitle != null) && 
			            (invTitle.equalsIgnoreCase(ChatColor.GOLD + "Abusing" + ChatColor.WHITE + " - menu")))
			          {
			            event.setCancelled(true);
			            if (event.getCurrentItem() != null) {
			              if (item.getType() == Material.COMPASS) {
			                player.closeInventory();
			              } else if (item.getType() == Material.PLAYER_HEAD) {
			                for (Player OPs : Bukkit.getServer().getOnlinePlayers()) {
			                  if ((OPs.isOp() | OPs.hasPermission("ureport.onduty"))) {
			                    if (getConfig().getString(OPs.getName()) != null)
			                    {
			                      if (getConfig().getBoolean(OPs.getName()))
			                      {
			                        player.sendMessage(this.RSuccess);
			                        
			                        this.reportedBy = this.reportedBy.replaceAll("-player-", player.getName());
			                        this.reportedBy = this.reportedBy.replaceAll("-reason-", this.Reason1);
			                        
			                        this.forReported = this.forReported.replaceAll("&", "§");
			                        
			                        this.messageToOP = this.messageToOP.replaceAll("-player-", player.getName());
			                        this.messageToOP = this.messageToOP.replaceAll("-reportedPlayer-", player2);
			                        this.messageToOP = this.messageToOP.replaceAll("-reason-", this.Reason1);
			                        
			                        String withoutColors = ChatColor.stripColor(player2);
			    					Player toPlayer = getServer().getPlayer(withoutColors);
			                        toPlayer.sendMessage(this.reportedBy + " " + ChatColor.GREEN + player.getName() + " " + this.forReported + ChatColor.GREEN + " " + this.Reason1);
			                        OPs.sendMessage(player.getName() + " " + this.messageToOP + " " + ChatColor.GREEN + player2 + " " + this.forReported + ChatColor.GREEN + " " + this.Reason1);
			                        
			                        String motivul = "Reason1";
			                        int reported_times = 0;
			                        if(getConfig().get("Reports." + motivul + "." + player.getName()) != null) reported_times = getConfig().getInt("Reports." + motivul + "." + player.getName());
			                        getConfig().getConfigurationSection("Reports." + motivul).set(player.getName(), reported_times+1);
			                        
			                        saveConfig();
			                        reloadConfig();
			                      }
			                      if (!getConfig().getBoolean(OPs.getName())) {
			                        player.sendMessage(this.NoAdminsDuty);
			                      }
			                    }
			                    else
			                    {
			                      player.sendMessage(this.NoAdminsDuty);
			                    }
			                  }
			                }
			              }
			            }
			          }
			        }
			        if ((event.getWhoClicked() instanceof Player))
			        {
			          if ((invTitle != null) && 
			            (invTitle.equalsIgnoreCase(ChatColor.GOLD + "Spamming" + ChatColor.WHITE + " - menu")))
			          {
			            event.setCancelled(true);
			            if (event.getCurrentItem() != null) {
			              if (item.getType() == Material.COMPASS) {
			                player.closeInventory();
			              } else if (item.getType() == Material.PLAYER_HEAD) {
			                for (Player OPs : Bukkit.getServer().getOnlinePlayers()) {
			                  if ((OPs.isOp() | OPs.hasPermission("ureport.onduty"))) {
			                    if (getConfig().getString(OPs.getName()) != null)
			                    {
			                      if (getConfig().getBoolean(OPs.getName()))
			                      {
			                        player.sendMessage(this.RSuccess);
			                        
			                        this.reportedBy = this.reportedBy.replaceAll("-player-", player.getName());
			                        this.reportedBy = this.reportedBy.replaceAll("-reason-", this.Reason2);
			                        
			                        this.forReported = this.forReported.replaceAll("&", "§");
			                        
			                        this.messageToOP = this.messageToOP.replaceAll("-player-", player.getName());
			                        this.messageToOP = this.messageToOP.replaceAll("-reportedPlayer-", player2);
			                        this.messageToOP = this.messageToOP.replaceAll("-reason-", this.Reason2);
			                        
			                        String withoutColors = ChatColor.stripColor(player2);
			    					Player toPlayer = getServer().getPlayer(withoutColors);
			                        toPlayer.sendMessage(this.reportedBy + " " + ChatColor.GREEN + player.getName() + " " + this.forReported + ChatColor.GREEN + " " + this.Reason2);
			                        OPs.sendMessage(player.getName() + " " + this.messageToOP + " " + ChatColor.GREEN + player2 + " " + this.forReported + ChatColor.GREEN + " " + this.Reason2);
			                        
			                        String motivul = "Reason2";
			                        int reported_times = 0;
			                        if(getConfig().get("Reports." + motivul + "." + player.getName()) != null) reported_times = getConfig().getInt("Reports." + motivul + "." + player.getName());
			                        getConfig().getConfigurationSection("Reports." + motivul).set(player.getName(), reported_times+1);
			                        
			                        saveConfig();
			                        reloadConfig();
			                      }
			                      if (!getConfig().getBoolean(OPs.getName())) {
			                        player.sendMessage(this.NoAdminsDuty);
			                      }
			                    }
			                    else
			                    {
			                      player.sendMessage(this.NoAdminsDuty);
			                    }
			                  }
			                }
			              }
			            }
			          }
			        }
			        if ((event.getWhoClicked() instanceof Player))
			        {
			          if ((invTitle != null) && 
			            (invTitle.equalsIgnoreCase(ChatColor.GOLD + "Griefing" + ChatColor.WHITE + " - menu")))
			          {
			            event.setCancelled(true);
			            if (event.getCurrentItem() != null) {
			              if (item.getType() == Material.COMPASS) {
			                player.closeInventory();
			              } else if (item.getType() == Material.PLAYER_HEAD) {
			                for (Player OPs : Bukkit.getServer().getOnlinePlayers()) {
			                  if ((OPs.isOp() | OPs.hasPermission("ureport.onduty"))) {
			                    if (getConfig().getString(OPs.getName()) != null)
			                    {
			                      if (getConfig().getBoolean(OPs.getName()))
			                      {
			                        player.sendMessage(this.RSuccess);
			                        
			                        this.reportedBy = this.reportedBy.replaceAll("-player-", player.getName());
			                        this.reportedBy = this.reportedBy.replaceAll("-reason-", this.Reason3);
			                        
			                        this.forReported = this.forReported.replaceAll("&", "§");
			                        
			                        this.messageToOP = this.messageToOP.replaceAll("-player-", player.getName());
			                        this.messageToOP = this.messageToOP.replaceAll("-reportedPlayer-", player2);
			                        this.messageToOP = this.messageToOP.replaceAll("-reason-", this.Reason3);
			                        
			                        String withoutColors = ChatColor.stripColor(player2);
			    					Player toPlayer = getServer().getPlayer(withoutColors);
			                        toPlayer.sendMessage(this.reportedBy + " " + ChatColor.GREEN + player.getName() + " " + this.forReported + ChatColor.GREEN + " " + this.Reason3);
			                        OPs.sendMessage(player.getName() + " " + this.messageToOP + " " + ChatColor.GREEN + player2 + " " + this.forReported + ChatColor.GREEN + " " + this.Reason3);
			                        if (this.griefTp)
			                        {
			                          double Px = toPlayer.getLocation().getX();
			                          double Py = toPlayer.getLocation().getY();
			                          double Pz = toPlayer.getLocation().getZ();
			                          World Pworld = toPlayer.getWorld();
			                          float Ppitch = toPlayer.getLocation().getPitch();
			                          float Pyaw = toPlayer.getLocation().getYaw();
			                          
			                          String numele_player = toPlayer.getName();
			                          
			                          getConfig().set("TeleportTo." + numele_player + ".world", Pworld.getName());
			                          getConfig().set("TeleportTo." + numele_player + ".x", Double.valueOf(Px));
			                          getConfig().set("TeleportTo." + numele_player + ".y", Double.valueOf(Py));
			                          getConfig().set("TeleportTo." + numele_player + ".z", Double.valueOf(Pz));
			                          getConfig().set("TeleportTo." + numele_player + ".pitch", Float.valueOf(Ppitch));
			                          getConfig().set("TeleportTo." + numele_player + ".yaw", Float.valueOf(Pyaw));
			                          
			                          saveConfig();
			                          reloadConfig();
			                        }
			                        
			                        String motivul = "Reason3";
			                        int reported_times = 0;
			                        if(getConfig().get("Reports." + motivul + "." + player.getName()) != null) reported_times = getConfig().getInt("Reports." + motivul + "." + player.getName());
			                        getConfig().getConfigurationSection("Reports." + motivul).set(player.getName(), reported_times+1);
			                        
			                        saveConfig();
			                        reloadConfig();
			                      }
			                      if (!getConfig().getBoolean(OPs.getName())) {
			                        player.sendMessage(this.NoAdminsDuty);
			                      }
			                    }
			                    else
			                    {
			                      player.sendMessage(this.NoAdminsDuty);
			                    }
			                  }
			                }
			              }
			            }
			          }
			        }
			        if ((event.getWhoClicked() instanceof Player))
			        {
			          if ((invTitle != null) && 
			            (invTitle.equalsIgnoreCase(ChatColor.GOLD + "Cheating" + ChatColor.WHITE + " - menu")))
			          {
			            event.setCancelled(true);
			            if (event.getCurrentItem() != null) {
			              if (item.getType() == Material.COMPASS) {
			                player.closeInventory();
			              } else if (item.getType() == Material.PLAYER_HEAD) {
			                for (Player OPs : Bukkit.getServer().getOnlinePlayers()) {
			                  if ((OPs.isOp() | OPs.hasPermission("ureport.onduty"))) {
			                    if (getConfig().getString(OPs.getName()) != null)
			                    {
			                      if (getConfig().getBoolean(OPs.getName()))
			                      {
			                        player.sendMessage(this.RSuccess);
			                        
			                        this.reportedBy = this.reportedBy.replaceAll("-player-", player.getName());
			                        this.reportedBy = this.reportedBy.replaceAll("-reason-", this.Reason4);
			                        
			                        this.forReported = this.forReported.replaceAll("&", "§");
			                        
			                        this.messageToOP = this.messageToOP.replaceAll("-player-", player.getName());
			                        this.messageToOP = this.messageToOP.replaceAll("-reportedPlayer-", player2);
			                        this.messageToOP = this.messageToOP.replaceAll("-reason-", this.Reason4);
			                        
			                        String withoutColors = ChatColor.stripColor(player2);
			    					Player toPlayer = getServer().getPlayer(withoutColors);
			                        toPlayer.sendMessage(this.reportedBy + " " + ChatColor.GREEN + player.getName() + " " + this.forReported + ChatColor.GREEN + " " + this.Reason4);
			                        OPs.sendMessage(player.getName() + " " + this.messageToOP + " " + ChatColor.GREEN + player2 + " " + this.forReported + ChatColor.GREEN + " " + this.Reason4);
			                        
			                        String motivul = "Reason4";
			                        int reported_times = 0;
			                        if(getConfig().get("Reports." + motivul + "." + player.getName()) != null) reported_times = getConfig().getInt("Reports." + motivul + "." + player.getName());
			                        getConfig().getConfigurationSection("Reports." + motivul).set(player.getName(), reported_times+1);
			                        
			                        saveConfig();
			                        reloadConfig();
			                      }
			                      if (!getConfig().getBoolean(OPs.getName())) {
			                        player.sendMessage(this.NoAdminsDuty);
			                      }
			                    }
			                    else
			                    {
			                      player.sendMessage(this.NoAdminsDuty);
			                    }
			                  }
			                }
			              }
			            }
			          }
			        }
			        if ((event.getWhoClicked() instanceof Player))
			        {
			          if ((invTitle != null) && 
			            (invTitle.equalsIgnoreCase(ChatColor.GOLD + "Other" + ChatColor.WHITE + " - menu")))
			          {
			            event.setCancelled(true);
			            if (event.getCurrentItem() != null) {
			              if (item.getType() == Material.COMPASS) {
			                player.closeInventory();
			              } else if (item.getType() == Material.PLAYER_HEAD) {
			                for (Player OPs : Bukkit.getServer().getOnlinePlayers()) {
			                  if ((OPs.isOp() | OPs.hasPermission("ureport.onduty"))) {
			                    if (getConfig().getString(OPs.getName()) != null)
			                    {
			                      if (getConfig().getBoolean(OPs.getName()))
			                      {
			                        player.sendMessage(this.RSuccess);
			                        
			                        this.reportedBy = this.reportedBy.replaceAll("-player-", player.getName());
			                        this.reportedBy = this.reportedBy.replaceAll("-reason-", this.Reason5);
			                        
			                        this.forReported = this.forReported.replaceAll("&", "§");
			                        
			                        this.messageToOP = this.messageToOP.replaceAll("-player-", player.getName());
			                        this.messageToOP = this.messageToOP.replaceAll("-reportedPlayer-", player2);
			                        this.messageToOP = this.messageToOP.replaceAll("-reason-", this.Reason5);
			                        
			                        String withoutColors = ChatColor.stripColor(player2);
			    					Player toPlayer = getServer().getPlayer(withoutColors);
			                        toPlayer.sendMessage(this.reportedBy + " " + ChatColor.GREEN + player.getName() + " " + this.forReported + ChatColor.GREEN + " " + this.Reason5);
			                        OPs.sendMessage(player.getName() + " " + this.messageToOP + " " + ChatColor.GREEN + player2 + " " + this.forReported + ChatColor.GREEN + " " + this.Reason5);
			                        
			                        String motivul = "Reason5";
			                        int reported_times = 0;
			                        if(getConfig().get("Reports." + motivul + "." + player.getName()) != null) reported_times = getConfig().getInt("Reports." + motivul + "." + player.getName());
			                        getConfig().getConfigurationSection("Reports." + motivul).set(player.getName(), reported_times+1);
			                        
			                        saveConfig();
			                        reloadConfig();
			                      }
			                      if (!getConfig().getBoolean(OPs.getName())) {
			                        player.sendMessage(this.NoAdminsDuty);
			                      }
			                    }
			                    else
			                    {
			                      player.sendMessage(this.NoAdminsDuty);
			                    }
			                  }
			                }
			              }
			            }
			          }
			        }
			        if ((event.getWhoClicked() instanceof Player))
			        {
			          if ((invTitle != null) && 
			            (invTitle.equalsIgnoreCase(ChatColor.WHITE + "Select reason:")))
			          {
			            event.setCancelled(true);
			            if (event.getCurrentItem() != null) {
			              if (item.getType() == Material.COMPASS)
			              {
			                player.closeInventory();
			              }
			              else if (item.getType() == Material.WHITE_WOOL)
			              {
			                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Abusing"))
			                {
			                  player.closeInventory();
			                  openGui(player, "Abusing", "Abusing");
			                }
			                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Spamming"))
			                {
			                  player.closeInventory();
			                  openGui(player, "Spamming", "Spamming");
			                }
			                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "Griefing"))
			                {
			                  player.closeInventory();
			                  openGui(player, "Griefing", "Griefing");
			                }
			                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GRAY + "Cheating"))
			                {
			                  player.closeInventory();
			                  openGui(player, "Cheating", "Cheating");
			                }
			                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Other"))
			                {
			                  player.closeInventory();
			                  openGui(player, "Other", "Other");
			                }
			              }
			            }
			          }
			        }
			        if ((event.getWhoClicked() instanceof Player))
			        {
			          if ((invTitle != null) && 
			            (invTitle.equalsIgnoreCase(ChatColor.GOLD + "Players")))
			          {
			            event.setCancelled(true);
			            if (event.getCurrentItem() != null) {
			              if (item.getType() == Material.COMPASS)
			              {
			                player.closeInventory();
			              }
			              else if (item.getType() == Material.PLAYER_HEAD)
			              {
			                Bukkit.broadcastMessage(ChatColor.GOLD + "User named " + player2 + " has been banned!");
			                player.closeInventory();
			                getServer().banIP(player2);
			              }
			            }
			          }
			        }
			    }
		    } 
	  }
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    this.UReport1 = this.UReport1.replaceAll("&", "§");
    this.UReport2 = this.UReport2.replaceAll("&", "§");
    this.UReport3 = this.UReport3.replaceAll("&", "§");
    this.UReport4 = this.UReport4.replaceAll("&", "§");
    this.UReport5 = this.UReport5.replaceAll("&", "§");
    this.NR1 = this.NR1.replaceAll("&", "§");
    this.NR2 = this.NR2.replaceAll("&", "§");
    this.NR3 = this.NR3.replaceAll("&", "§");
    this.NR4 = this.NR4.replaceAll("&", "§");
    this.NR5 = this.NR5.replaceAll("&", "§");
    this.reportedBy = this.reportedBy.replaceAll("&", "§");
    this.messageToOP = this.messageToOP.replaceAll("&", "§");
    
    this.ConfigReset = this.ConfigReset.replaceAll("&", "§");
    this.ConfigPlayerReset = this.ConfigPlayerReset.replaceAll("&", "§");
    
    this.RSuccess = this.RSuccess.replaceAll("&", "§");
    this.NoAdminsDuty = this.NoAdminsDuty.replaceAll("&", "§");
    this.PlayerNotFound = this.PlayerNotFound.replaceAll("&", "§");
    this.NoPermission = this.NoPermission.replaceAll("&", "§");
    this.AlreadyONDuty = this.AlreadyONDuty.replaceAll("&", "§");
    this.AlreadyOFFDuty = this.AlreadyOFFDuty.replaceAll("&", "§");
    this.NowONDuty = this.NowONDuty.replaceAll("&", "§");
    this.NowOFFDuty = this.NowOFFDuty.replaceAll("&", "§");
    
    Player player = (Player)sender;
    if (commandLabel.equalsIgnoreCase("reset")) {
      if ((sender instanceof Player))
      {
        if (player.hasPermission("ureport.reset"))
        {
          if (args.length == 0) {
            player.sendMessage(this.UReport3);
          }
          else if (args.length == 1)
          {
            if (args[0].equalsIgnoreCase("report"))
            {
              File configFile = new File(getDataFolder(), "config.yml");
              configFile.delete();
              saveDefaultConfig();
              reloadConfig();
              this.griefTp = false;
              player.sendMessage(this.ConfigReset);
            }
            else
            {
              Player args0 = Bukkit.getServer().getPlayer(args[0]);
              getConfig().set(args0 + ">", null);
              saveConfig();
              reloadConfig();
              player.sendMessage(this.ConfigPlayerReset);
            }
          }
          else {
            player.sendMessage(this.UReport3);
          }
        }
        else if (!player.hasPermission("ureport.reset"))
        {
          player.sendMessage(this.NoPermission);
        }
      }
      else {
        System.out.println(this.ConsoleCMD);
      }
    }
    if (commandLabel.equalsIgnoreCase("onduty")) {
      if ((sender instanceof Player))
      {
        if (sender.hasPermission("ureport.onduty"))
        {
          if (getConfig().getString(sender.getName()) != null)
          {
            if (getConfig().getBoolean(sender.getName())) {
              sender.sendMessage(this.AlreadyONDuty);
            }
            if (!getConfig().getBoolean(sender.getName()))
            {
              getConfig().set(sender.getName(), Boolean.valueOf(true));
              this.DutyON = this.DutyON.replaceAll("&", "§");
              saveConfig();
              reloadConfig();
              sender.sendMessage(this.NowONDuty);
              Bukkit.broadcastMessage(sender.getName() + " " + this.DutyON);
            }
          }
          else
          {
            getConfig().set(sender.getName(), Boolean.valueOf(true));
            this.DutyON = this.DutyON.replaceAll("&", "§");
            saveConfig();
            reloadConfig();
            sender.sendMessage(this.NowONDuty);
            Bukkit.broadcastMessage(sender.getName() + " " + this.DutyON);
          }
        }
        else if (!sender.hasPermission("ureport.onduty")) {
          sender.sendMessage(this.NoPermission);
        }
      }
      else {
        System.out.println(this.ConsoleCMD);
      }
    }
    if (commandLabel.equalsIgnoreCase("offduty")) {
      if ((sender instanceof Player))
      {
        if (sender.hasPermission("ureport.offduty"))
        {
          if (getConfig().getString(sender.getName()) != null)
          {
            if (!getConfig().getBoolean(sender.getName())) {
              sender.sendMessage(this.AlreadyOFFDuty);
            }
            if (getConfig().getBoolean(sender.getName()))
            {
              getConfig().set(sender.getName(), Boolean.valueOf(false));
              this.DutyOFF = this.DutyOFF.replaceAll("&", "§");
              saveConfig();
              reloadConfig();
              sender.sendMessage(this.NowOFFDuty);
              Bukkit.broadcastMessage(sender.getName() + " " + this.DutyOFF);
            }
          }
          else
          {
            getConfig().set(sender.getName(), Boolean.valueOf(false));
            this.DutyOFF = this.DutyOFF.replaceAll("&", "§");
            saveConfig();
            reloadConfig();
            sender.sendMessage(this.NowOFFDuty);
            Bukkit.broadcastMessage(sender.getName() + " " + this.DutyOFF);
          }
        }
        else if (!sender.hasPermission("ureport.offduty")) {
          sender.sendMessage(this.NoPermission);
        }
      }
      else {
        System.out.println(this.ConsoleCMD);
      }
    }
    if (commandLabel.equalsIgnoreCase("grieftp")) {
      if ((sender instanceof Player))
      {
        if (player.hasPermission("ureport.grieftp"))
        {
          if (args.length == 0)
          {
            player.sendMessage(ChatColor.RED + "[USAGE] " + ChatColor.GREEN + "/grieftp <player>");
          }
          else if (args.length == 1)
          {
        	if(!this.griefTp) {
        		player.sendMessage(ChatColor.RED + "The variable " + ChatColor.GREEN + " GriefTeleport " + ChatColor.RED + "needs to be set to " + ChatColor.GREEN + "true " + ChatColor.RED + "for teleporting to the griefer!");
        	} else {
        		if(getConfig().get("TeleportTo." + args[0].toString()) != null) {
                    double x = getConfig().getDouble("TeleportTo." + args[0].toString() + ".x");
                    double y = getConfig().getDouble("TeleportTo." + args[0].toString() + ".y");
                    double z = getConfig().getDouble("TeleportTo." + args[0].toString() + ".z");
                    
                    float pitch = (float)getConfig().getDouble("TeleportTo." + args[0].toString() + ".pitch");
                    float yaw = (float)getConfig().getDouble("TeleportTo." + args[0].toString() + ".yaw");
                    
                    World Pworld = player.getWorld();
                    
                    Location l = new Location(Pworld, x, y+1, z);
                    l.setPitch(pitch);
                    l.setYaw(yaw);
                    
                    player.teleport(l);
                    
                    player.sendMessage(ChatColor.RED + "You have been teleported to the location where " + ChatColor.GREEN + args[0].toString() + ChatColor.RED + " was reported.");
            	} else player.sendMessage(ChatColor.RED + "There is no records of grief teleportation for player " + ChatColor.GREEN + args[0].toString());
        	}
          }
        }
        else if (!player.hasPermission("ureport.grieftp")) {
          player.sendMessage(this.NoPermission);
        }
      }
      else {
        System.out.println(this.ConsoleCMD);
      }
    }
    if (commandLabel.equalsIgnoreCase("removegtp")) {
      if ((sender instanceof Player))
      {
        if (player.hasPermission("ureport.removegtp"))
        {
          if (args.length == 0)
          {
            player.sendMessage(ChatColor.RED + "[USAGE] " + ChatColor.GREEN + "/removegtp <player>");
          }
          else if (args.length == 1)
          {
            getConfig().set("TeleportTo." + args[0], null);
            
            saveConfig();
            reloadConfig();
            
            player.sendMessage(ChatColor.RED + "You set teleport details for player " + ChatColor.GREEN + args[0].toString() + ChatColor.RED + " to null");
          }
        }
        else if (!player.hasPermission("ureport.removegtp")) {
          player.sendMessage(this.NoPermission);
        }
      }
      else {
        System.out.println(this.ConsoleCMD);
      }
    }
    if (commandLabel.equalsIgnoreCase("report")) {
      if ((sender instanceof Player))
      {
        if (player.hasPermission("ureport.use")) {
          openGui(player, "player", "player");
        } else if (!player.hasPermission("ureport.use")) {
          player.sendMessage(this.NoPermission);
        }
      }
      else {
        System.out.println(this.ConsoleCMD);
      }
    }
    
    if(commandLabel.equalsIgnoreCase("reports")) {
    	if((sender instanceof Player)) {
    		if(player.hasPermission("ureport.reports")) {
    			
    			if(args.length == 0) {
    				player.sendMessage(this.UReport5);
    			} else if(args.length == 1) {
    				
    				String pname = getServer().getPlayer(args[0]).getName();
    				
    				int reports = 0;
    				
    				player.sendMessage(ChatColor.BLUE + "Reports Summary for " + ChatColor.YELLOW + pname);
    				
    				if(getConfig().getInt("Reports.Reason1." + pname) != 0) {
        				int reason1 = getConfig().getInt("Reports.Reason1." + pname);
        				player.sendMessage(ChatColor.AQUA + "Reports for " + this.Reason1.replaceAll("&", "§") + ChatColor.AQUA + ": " + ChatColor.RED + reason1);
        				reports = reports + reason1;
    				}
    				if(getConfig().getInt("Reports.Reason2." + pname) != 0) {
        				int reason2 = getConfig().getInt("Reports.Reason2." + pname);
        				player.sendMessage(ChatColor.AQUA + "Reports for " + this.Reason2.replaceAll("&", "§") + ChatColor.AQUA + ": " + ChatColor.RED + reason2);
        				reports = reports + reason2;
    				}
    				if(getConfig().getInt("Reports.Reason3." + pname) != 0) {
        				int reason3 = getConfig().getInt("Reports.Reason3." + pname);
        				player.sendMessage(ChatColor.AQUA + "Reports for " + this.Reason3.replaceAll("&", "§") + ChatColor.AQUA + ": " + ChatColor.RED + reason3);
        				reports = reports + reason3;
    				}
    				if(getConfig().getInt("Reports.Reason4." + pname) != 0) {
        				int reason4 = getConfig().getInt("Reports.Reason4." + pname);
        				player.sendMessage(ChatColor.AQUA + "Reports for " + this.Reason4.replaceAll("&", "§") + ChatColor.AQUA + ": " + ChatColor.RED + reason4);
        				reports = reports + reason4;
    				}
    				if(getConfig().getInt("Reports.Reason5." + pname) != 0) {
        				int reason5 = getConfig().getInt("Reports.Reason5." + pname);
        				player.sendMessage(ChatColor.AQUA + "Reports for " + this.Reason5.replaceAll("&", "§") + ChatColor.AQUA + ": " + ChatColor.RED + reason5);
        				reports = reports + reason5;
    				}
    				
    				if(reports == 0) player.sendMessage(ChatColor.RED + "[UReport] This user has no reports submitted.");
    				
    			} else {
    				player.sendMessage(this.UReport5);
    			}
    			
    		} else {
    			player.sendMessage(this.NoPermission);
    		}
    	} else {
    		System.out.println(this.ConsoleCMD);
    	}
    }
    
    if(commandLabel.equalsIgnoreCase("ureport")) {
    	if((sender instanceof Player)) {
    		if(player.hasPermission("ureport.ureport")) {
    			if(args.length == 0) {
    				player.sendMessage(this.UReport4);
    				player.sendMessage(ChatColor.RED + "Available variables: GriefTeleport");
    			} else if(args.length == 1) {
    				if(args[0].equalsIgnoreCase("GriefTeleport")) {
    					player.sendMessage(ChatColor.RED + "Available options: true/false");
    					player.sendMessage(ChatColor.GREEN + "Current option: " + this.griefTp);
    				} else {
        				player.sendMessage(this.UReport4);
        				player.sendMessage(ChatColor.RED + "Available variables: GriefTeleport");
    				}
    			} else if(args.length == 2) {
    				if(args[0].equalsIgnoreCase("GriefTeleport")) {
        				boolean option = Boolean.parseBoolean(args[1]);
        				this.griefTp = option;
        				
        				getConfig().set("GriefTeleport", option);
        				
        				saveConfig();
        				reloadConfig();
        				
        				player.sendMessage(ChatColor.RED + "Variable " + ChatColor.GREEN + args[0] + ChatColor.RED + " set to " + ChatColor.GREEN + args[1]);
    				} else {
    					player.sendMessage(ChatColor.RED + "Variable " + ChatColor.YELLOW + args[0] + ChatColor.RED + " is not available!");
    					player.sendMessage(ChatColor.RED + "Available variables: GriefTeleport");
    				}
    			}

    		} else {
    			player.sendMessage(this.NoPermission);
    		}
    	} else {
    		System.out.println(this.ConsoleCMD);
    	}
    }
    return false;
  }
}
